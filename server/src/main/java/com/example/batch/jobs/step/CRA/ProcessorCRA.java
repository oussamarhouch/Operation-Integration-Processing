package com.example.batch.jobs.step.CRA;

import com.example.batch.models.*;
import com.example.batch.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class ProcessorCRA implements ItemProcessor<String, String[]> {
    private final LotOperationRepository lotOperationRepository;
    private final LotRejetRepository lotRejetRepository;
    private final CRARepository craRepository;
    private final OperationRepository operationRepository;
    private final RejetRepository rejetRepository;
    private final CRAOperationRepository craOperationRepository;
    protected final Logger logger;
    private String inputDirectoryPath;

    public ProcessorCRA(LotOperationRepository lotOperationRepository, LotRejetRepository lotRejetRepository,
                        CRARepository craRepository, OperationRepository operationRepository,
                        RejetRepository rejetRepository, CRAOperationRepository craOperationRepository, String inputDirectoryPath) {
        this.lotOperationRepository = lotOperationRepository;
        this.lotRejetRepository = lotRejetRepository;
        this.craRepository = craRepository;
        this.operationRepository = operationRepository;
        this.rejetRepository = rejetRepository;
        this.craOperationRepository = craOperationRepository;
        this.logger = LogManager.getLogger(getClass());
        this.inputDirectoryPath = inputDirectoryPath;
    }

    public String[] openFile(String fileName) throws IOException {
        StringBuilder input = new StringBuilder();
        File file = new File(inputDirectoryPath + "/CRA/" + fileName);
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

    public String[] title(String fileName) {
        return fileName.split("\\.");
    }

    public String[] integrerCRA(String fileName) throws IOException {
        String[] title = title(fileName);
        String identifiantBanque = title[0];
        String identifiantSource = title[1];
        String numeroLot = title[2];
        String typeOperation = title[3];
        String devise = title[4];
        String[] data = openFile(fileName);

        String codeRejet = data[0].substring(36, 38);
        String[] lines = Arrays.copyOfRange(data, 1, data.length);

        CRA savedCRA;
        if (Objects.equals(typeOperation, "010") || Objects.equals(typeOperation, "012") ||
                Objects.equals(typeOperation, "013") || Objects.equals(typeOperation, "020") ||
                Objects.equals(typeOperation, "031") || Objects.equals(typeOperation, "060")) {
            LocalDate currentDate = LocalDate.now();

            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            LotOperation lotOperation = lotOperationRepository.findLotOperationByDateAndIdentifiantBanqueAndIdentifiantSourceAndNumeroLotAndTypeOperationAndDevise(
                    formattedDate, identifiantBanque, identifiantSource, numeroLot, typeOperation, devise);
            CRA cra = new CRA(lotOperation, null, codeRejet, typeOperation);
            savedCRA = craRepository.save(cra);

            for (String line : lines) {
                String RIO = line.substring(0, 40);
                String referenceOperation;
                String codeRejetOperation = line.substring(587, 590);
                long montant;
                switch (typeOperation) {
                    case "010":
                        montant = Long.parseLong(line.substring(302, 317));
                        referenceOperation = line.substring(40, 52);
                        break;
                    case "012":
                        montant = Long.parseLong(line.substring(80, 95));
                        referenceOperation = line.substring(40, 52);
                        break;
                    case "013":
                        montant = Long.parseLong(line.substring(173, 188));
                        referenceOperation = line.substring(40, 52);
                        break;
                    case "020":
                        montant = Long.parseLong(line.substring(287, 302));
                        referenceOperation = line.substring(39, 51);
                        break;
                    case "031":
                        montant = Long.parseLong(line.substring(290, 305));
                        referenceOperation = line.substring(39, 51);
                        break;
                    default:
                        montant = Long.parseLong(line.substring(368, 383));
                        referenceOperation = line.substring(39, 51);
                        break;
                }

                Operation operation = operationRepository.findByLotOperationAndReferenceOperation(lotOperation, referenceOperation);

                CRAOperation craOperation = new CRAOperation(operation, null, savedCRA, codeRejetOperation, RIO, montant);

                craOperationRepository.save(craOperation);
            }
            return null;

        } else if (Objects.equals(typeOperation, "120") || Objects.equals(typeOperation, "131") || Objects.equals(typeOperation, "160")) {
            LocalDate currentDate = LocalDate.now();

            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            LotRejet lotRejet = lotRejetRepository.findByIdentifiantBanqueAndIdentifiantSourceAndNumeroLotAndTypeOperationAndDeviseAndDate(
                    identifiantBanque, identifiantSource, numeroLot, typeOperation, devise, formattedDate);
            CRA cra = new CRA(null, lotRejet, codeRejet, typeOperation);
            savedCRA = craRepository.save(cra);

            for (String line : lines) {
                String RIO = line.substring(0, 40);
                String referenceOperation = line.substring(46, 58);
                String codeRejetOperation = line.substring(137, 140);

                Rejet rejet = rejetRepository.findByLotRejetAndReferenceOperation(lotRejet, referenceOperation);

                CRAOperation craOperation = new CRAOperation(null, rejet, savedCRA, codeRejetOperation, RIO, 0);

                craOperationRepository.save(craOperation);
            }

            return null;
        } else {
            return null;

        }
    }

    @Override
    public String[] process(String data) throws Exception {
        logger.info("\n\n " +
                "================================================================= Intégration du CRA :" + data + " =================================================================" +
                "\n\n");
        return integrerCRA(data);
    }
}

