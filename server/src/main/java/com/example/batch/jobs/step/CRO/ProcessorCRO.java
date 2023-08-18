package com.example.batch.jobs.step.CRO;

import com.example.batch.models.CRO;
import com.example.batch.models.CROOperation;
import com.example.batch.repository.CROOperationRepository;
import com.example.batch.repository.CRORepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ProcessorCRO implements ItemProcessor<String, String[]> {
    protected final Logger logger;
    private final String inputDirectoryPath;
    private final CRORepository croRepository;
    private final CROOperationRepository croOperationRepository;

    public ProcessorCRO(String inputDirectoryPath, CRORepository croRepository, CROOperationRepository croOperationRepository) {
        this.logger = LogManager.getLogger(getClass());
        this.inputDirectoryPath = inputDirectoryPath;
        this.croRepository = croRepository;
        this.croOperationRepository = croOperationRepository;
    }

    public String[] openFile(String fileName) throws IOException {
        StringBuilder input = new StringBuilder();
        File file = new File(inputDirectoryPath + "/CRO/" + fileName);
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

    public String[] integrerCRO(String fileName) throws IOException {
        String banqueDest = title(fileName)[0];
        String banqueSource = title(fileName)[1];
        String numeroLot = title(fileName)[2];
        String typeOperation = title(fileName)[3];

        if (Objects.equals(typeOperation, "010") || Objects.equals(typeOperation, "012") ||
                Objects.equals(typeOperation, "013") || Objects.equals(typeOperation, "020") ||
                Objects.equals(typeOperation, "031") || Objects.equals(typeOperation, "060")) {
            String[] input = openFile(fileName);

            long montant = Long.parseLong(input[0].substring(20, 36));
            CRO cro = new CRO(banqueDest, banqueSource, numeroLot, typeOperation, montant);
            CRO savedCRO = croRepository.save(cro);

            String[] data = Arrays.copyOfRange(input, 1, input.length);
            for (String line : data) {
                String RIO = line.substring(0, 32);
                String date = line.substring(32, 36) + "-" + line.substring(36, 38) + "-" + line.substring(38, 40);
                long montantOp;
                if (Objects.equals(typeOperation, "010")) {
                    montantOp = Long.parseLong(line.substring(302, 317));
                } else if (Objects.equals(typeOperation, "012")) {
                    montantOp = Long.parseLong(line.substring(80, 95));
                } else if (Objects.equals(typeOperation, "013")) {
                    montantOp = Long.parseLong(line.substring(173, 188));
                } else if (Objects.equals(typeOperation, "020")) {
                    montantOp = Long.parseLong(line.substring(287, 302));
                } else if (Objects.equals(typeOperation, "031")) {
                    montantOp = Long.parseLong(line.substring(290, 305));
                } else {
                    montantOp = Long.parseLong(line.substring(368, 393));
                }

                CROOperation croOperation = new CROOperation(savedCRO, RIO, date, montantOp);
                croOperationRepository.save(croOperation);
            }

        } else {
            CRO cro = new CRO(banqueDest, banqueSource, numeroLot, typeOperation, 0);
            CRO savedCRO = croRepository.save(cro);
            String[] input = openFile(fileName);

            String[] data = Arrays.copyOfRange(input, 1, input.length);
            for (String line : data) {
                String RIO = line.substring(0, 32);
                String date = line.substring(32, 36) + "-" + line.substring(36, 38) + "-" + line.substring(38, 40);
                CROOperation croOperation = new CROOperation(savedCRO, RIO, date, 0);
                croOperationRepository.save(croOperation);
            }
        }

        return null;
    }

    @Override
    public String[] process(String data) throws Exception {
        logger.info("\n\n " +
                "================================================================= Intégration du CRO :" + data + " =================================================================" +
                "\n\n");
        return integrerCRO(data);
    }
}
