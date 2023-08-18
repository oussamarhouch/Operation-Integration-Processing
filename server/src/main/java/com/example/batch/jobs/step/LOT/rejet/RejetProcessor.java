package com.example.batch.jobs.step.LOT.rejet;

import com.example.batch.models.LotMere;
import com.example.batch.repository.LotMereRepository;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public abstract class RejetProcessor implements ItemProcessor<String, String[]> {
    protected final LotMereRepository lotsMereRepository;
    protected final Logger logger;
    private String inputDirectoryPath;
    private String outputDirectoryPath;

    public RejetProcessor(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        this.lotsMereRepository = lotsMereRepository;
        this.logger = LogManager.getLogger(getClass());
        this.inputDirectoryPath = inputDirectoryPath;
        this.outputDirectoryPath = outputDirectoryPath;
    }

    protected String[] openFile(String fileName) throws IOException {
        StringBuilder input = new StringBuilder();
        File file = new File(inputDirectoryPath + "/LOT/" + fileName);
        try {
            if (!file.exists()) {
                throw new IllegalArgumentException("File " + fileName + " is not found");
            }
        } catch (IllegalArgumentException e) {
            logger.error("Error occurred: " + e.getMessage(), e);
        }
        try (FileReader fileReader = new FileReader(file);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line).append("\n");
            }
        }
        return input.toString().split("\n");
    }

    protected String[] entete(String line, String operationType) {
        String id = line.substring(0, 4);
        String bankId = line.substring(4, 7);
        String sourceId = line.substring(7, 10);
        String lotNumber = line.substring(10, 13);
        String devise = line.substring(13, 16);
        String operationNumber = line.substring(16, 20);
        return new String[]{id, bankId, sourceId, lotNumber, operationType, devise, operationNumber};
    }

    protected String[] createLOTFile(String banqueRemettante, String source, int numeroLot, String typeOperation, String devise) throws IOException {
        String directoryPath = outputDirectoryPath + "/LOT/";
        String fileName = banqueRemettante + "." + source + "." + String.format("%03d", numeroLot) + "." + typeOperation + "." + devise + ".LOT";
        File file = new File(directoryPath + fileName);
        while (file.exists()) {
            numeroLot++;
            fileName = banqueRemettante + "." + source + "." + String.format("%03d", numeroLot) + "." + typeOperation + "." + devise + ".LOT";
            file = new File(directoryPath + fileName);
        }

        Path newFilePath = Paths.get(directoryPath + fileName);
        FileUtils.touch(new File(newFilePath.toString()));
        return new String[]{fileName, String.valueOf(numeroLot)};
    }

    protected void writeLOTFile(String fileName, String[] entete, String[] data) {
        try {
            String directoryPath = outputDirectoryPath + "/LOT/";
            File file = new File(directoryPath + fileName);

            StringBuilder content = new StringBuilder();
            String strEntete = "ELOT" + entete[1] + entete[2] + entete[3] + entete[5] + String.format("%04d", data.length) + "0".repeat(16);
            strEntete += " ".repeat(64 - strEntete.length()) + "\n";
            content.append(strEntete);

            for (String line : data) {
                content.append(line).append(" ".repeat(100 - line.length())).append("\n");
            }

            FileUtils.writeStringToFile(file, content.toString(), StandardCharsets.UTF_8);

            logger.info("Successfully wrote " + data.length + " lines to the file " + fileName);
        } catch (IOException e) {
            logger.error("An error occurred: " + e);
        }
    }

    public String[] decouper(String fileName) throws IOException {
        logger.info("\n\n " +
                "================================================================= Découpage du LOT : " + fileName + " =================================================================" +
                "\n\n");
        String[] data = openFile(fileName);
        int size = data.length;
        String[] input = Arrays.copyOfRange(data, 1, size);
        String SEPARATOR = "\\.";
        String[] fileNameParts = fileName.split(SEPARATOR);
        String operationType = fileNameParts[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String idLotmere = null;
        if (!lotsMereRepository.existsByEnteteAndDate(data[0], LocalDate.now().format(formatter))) {
            LotMere lotMere = new LotMere(data[0], operationType);
            LotMere savedLotMere = lotsMereRepository.save(lotMere);
            idLotmere = savedLotMere.getLotMereId();
        } else {
            LotMere existingLotMere = lotsMereRepository.findByEnteteAndDate(data[0], LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (existingLotMere != null) {
                idLotmere = existingLotMere.getLotMereId();
                logger.info("Lot already exists with id: " + idLotmere);
            } else {
                logger.error("Error retrieving existing LotMere.");
            }
        }
        String[] entete = entete(data[0], operationType);
        int numeroLot = Integer.parseInt(entete[3]) + 1;
        int nombreFichier = (size - 1) / 9999;
        String[] output = new String[nombreFichier + 2];
        output[0] = idLotmere;
        for (int i = 0; i < nombreFichier; i++) {
            String[] newFile = createLOTFile(entete[1], entete[2], numeroLot, entete[4], entete[5]);
            output[i + 1] = newFile[0];
            String[] enteteFichier = entete.clone();
            enteteFichier[3] = String.format("%03d", Integer.parseInt(newFile[1]));
            writeLOTFile(newFile[0], enteteFichier, Arrays.copyOfRange(input, i * 9999, (i + 1) * 9999));
            numeroLot++;
        }
        if (size % 10000 > 0) {
            String[] enteteFichier = entete.clone();
            String[] newFile = createLOTFile(entete[1], entete[2], numeroLot, entete[4], entete[5]);
            output[nombreFichier + 1] = newFile[0];
            enteteFichier[3] = String.format("%03d", Integer.parseInt(newFile[1]));
            writeLOTFile(newFile[0], enteteFichier, Arrays.copyOfRange(input, size - size % 9999, size - 1));
        }

        output = Arrays.stream(output)
                .filter(Objects::nonNull)
                .toArray(String[]::new);
        return output;

    }
}
