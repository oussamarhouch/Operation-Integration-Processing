package com.example.batch.jobs.step.CRL;

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

public class ProcessorCRL implements ItemProcessor<String, String[]> {
    private final LotOperationRepository lotOperationRepository;
    private final LotRejetRepository lotRejetRepository;
    private final CRLRepository crlRepository;
    private final OperationRepository operationRepository;
    private final RejetRepository rejetRepository;
    private final CRLOperationRepository crlOperationRepository;
    protected final Logger logger;
    private String inputDirectoryPath;

    public ProcessorCRL(LotOperationRepository lotOperationRepository, LotRejetRepository lotRejetRepository,
                        CRLRepository crlRepository, OperationRepository operationRepository,
                        RejetRepository rejetRepository, CRLOperationRepository crlOperationRepository, String inputDirectoryPath) {
        this.lotOperationRepository = lotOperationRepository;
        this.lotRejetRepository = lotRejetRepository;
        this.crlRepository = crlRepository;
        this.operationRepository = operationRepository;
        this.rejetRepository = rejetRepository;
        this.crlOperationRepository = crlOperationRepository;
        this.logger = LogManager.getLogger(getClass());
        this.inputDirectoryPath = inputDirectoryPath;
    }

    public String[] openFile(String fileName) throws IOException {
        StringBuilder input = new StringBuilder();
        File file = new File(inputDirectoryPath + "/CRL/" + fileName);
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

    public String[] integrerCRL(String fileName) throws IOException {
        String[] title = title(fileName);
        String identifiantBanque = title[0];
        String identifiantSource = title[1];
        String numeroLot = title[2];
        String typeOperation = title[3];
        String devise = title[4];
        String[] data = openFile(fileName);

        String codeRejet = data[0].substring(36, 38);
        String[] lines = Arrays.copyOfRange(data, 1, data.length);

        CRL savedCRL;
        if (Objects.equals(typeOperation, "010") || Objects.equals(typeOperation, "012") ||
                Objects.equals(typeOperation, "013") || Objects.equals(typeOperation, "020") ||
                Objects.equals(typeOperation, "031") || Objects.equals(typeOperation, "060")) {
            LocalDate currentDate = LocalDate.now();

            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            LotOperation lotOperation = lotOperationRepository.findLotOperationByDateAndIdentifiantBanqueAndIdentifiantSourceAndNumeroLotAndTypeOperationAndDevise(
                    formattedDate, identifiantBanque, identifiantSource, numeroLot, typeOperation, devise);

            CRL crl = new CRL(lotOperation, null, codeRejet, typeOperation);
            savedCRL = crlRepository.save(crl);

            for (String line : lines) {
                String referenceOperation;
                String codeRejetOperation = line.substring(547, 550);
                long montant;
                if (Objects.equals(typeOperation, "010")) {
                    montant = Long.parseLong(line.substring(262, 277));
                    referenceOperation = line.substring(4, 16);
                } else if (Objects.equals(typeOperation, "012")) {
                    montant = Long.parseLong(line.substring(40, 55));
                    referenceOperation = line.substring(4, 16);
                } else if (Objects.equals(typeOperation, "013")) {
                    montant = Long.parseLong(line.substring(133, 148));
                    referenceOperation = line.substring(4, 16);
                } else if (Objects.equals(typeOperation, "020")) {
                    montant = Long.parseLong(line.substring(247, 262));
                    referenceOperation = line.substring(3, 15);
                } else if (Objects.equals(typeOperation, "031")) {
                    montant = Long.parseLong(line.substring(250, 265));
                    referenceOperation = line.substring(3, 15);
                } else {
                    montant = Long.parseLong(line.substring(328, 343));
                    referenceOperation = line.substring(3, 15);
                }

                Operation operation = operationRepository.findByLotOperationAndReferenceOperation(lotOperation, referenceOperation);

                CRLOperation crlOperation = new CRLOperation(operation, null, savedCRL, codeRejetOperation, montant);

                crlOperationRepository.save(crlOperation);
            }
            return null;

        } else if (Objects.equals(typeOperation, "120") || Objects.equals(typeOperation, "131") || Objects.equals(typeOperation, "160")) {
            LocalDate currentDate = LocalDate.now();

            String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            LotRejet lotRejet = lotRejetRepository.findByIdentifiantBanqueAndIdentifiantSourceAndNumeroLotAndTypeOperationAndDeviseAndDate(
                    identifiantBanque, identifiantSource, numeroLot, typeOperation, devise, formattedDate);
            System.out.println(lotRejet);
            CRL crl = new CRL(null, lotRejet, codeRejet, typeOperation);
            savedCRL = crlRepository.save(crl);

            for (String line : lines) {
                String referenceOperation = line.substring(6, 18);
                String codeRejetOperation = line.substring(97, 100);

                Rejet rejet = rejetRepository.findByLotRejetAndReferenceOperation(lotRejet, referenceOperation);

                CRLOperation crlOperation = new CRLOperation(null, rejet, savedCRL, codeRejetOperation, 0);

                crlOperationRepository.save(crlOperation);
            }

            return null;
        } else {
            return null;

        }
    }

    @Override
    public String[] process(String data) throws Exception {
        logger.info("\n\n " +
                "================================================================= Intégration du CRL :" + data + " =================================================================" +
                "\n\n");
        return integrerCRL(data);
    }
}

