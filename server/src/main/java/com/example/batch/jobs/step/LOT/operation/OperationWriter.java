package com.example.batch.jobs.step.LOT.operation;

import com.example.batch.models.LotMere;
import com.example.batch.models.LotOperation;
import com.example.batch.models.Operation;
import com.example.batch.repository.LotMereRepository;
import com.example.batch.repository.LotOperationRepository;
import com.example.batch.repository.OperationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

@Component
public abstract class OperationWriter implements ItemWriter<String[]> {

    @Autowired
    private final LotMereRepository lotMereRepository;

    @Autowired
    private final LotOperationRepository lotOperationRepository;

    @Autowired
    private final OperationRepository operationRepository;

    protected final Logger logger;

    private final String outputDirectoryPath;

    public OperationWriter(LotOperationRepository lotOperationRepository, LotMereRepository lotMereRepository, OperationRepository operationRepository, @Value("${batch.app.outputDirectoryPath}") String outputDirectoryPath) {
        this.lotOperationRepository = lotOperationRepository;
        this.lotMereRepository = lotMereRepository;
        this.operationRepository = operationRepository;
        this.logger = LogManager.getLogger(getClass());
        this.outputDirectoryPath = outputDirectoryPath;
    }

    protected String[] entete(String line, String operationType) {
        String id = line.substring(0, 4);
        String bankId = line.substring(4, 7);
        String sourceId = line.substring(7, 10);
        String lotNumber = line.substring(10, 13);
        String devise = line.substring(13, 16);
        String operationNumber = line.substring(16, 20);
        String montantTotal = line.substring(20, 36);
        return new String[]{id, bankId, sourceId, lotNumber, operationType, devise, operationNumber, montantTotal};
    }

    public void saveLotsToDatabase(String[] item, String typeOperation, int indexReferenceOperation, int indexRIBdonneur, int indexRIBbeneficiaire, int indexMontant) throws IOException {
        String idLotMere = item[0];
        LotMere lotMere = lotMereRepository.findById(idLotMere).orElse(null);

        for (String fileName : item) {
            if (!Objects.equals(item[0], fileName)) {
                String[] input = openFile(fileName);

                String firstline = input[0];
                String[] entete = entete(firstline, typeOperation);
                String identifiantBanque = entete[1];
                String identifiantSource = entete[2];
                String numeroLot = entete[3];
                String devise = entete[5];
                int nombreOperation = Integer.parseInt(entete[6]);
                long montant = Long.parseLong(entete[7]);
                String[] data = Arrays.copyOfRange(input, 1, input.length);


                if (lotOperationRepository.existsOperationByIdentifiantBanqueAndIdentifiantSourceAndLotMereAndNumeroLotAndDeviseAndTypeOperationAndDateAndMontant(
                        identifiantBanque, identifiantSource, lotMere, numeroLot, devise, typeOperation, montant, String.valueOf(LocalDate.now()))) {
                    logger.error("Opération existe déja");
                } else {
                    LotOperation lotOperation = new LotOperation(typeOperation, identifiantBanque, identifiantSource, numeroLot, lotMere, devise, nombreOperation, montant);
                    LotOperation savedLotOperation = lotOperationRepository.save(lotOperation);
                    logger.info("Lot " + fileName + " registered successfully!");
                    if (Objects.equals(typeOperation, "012")) {
                        for (String line : data) {
                            String referenceOperation = line.substring(indexReferenceOperation, indexReferenceOperation + 12);
                            String donneur = line.substring(indexRIBdonneur, indexRIBdonneur + 3);
                            String beneficiaire = line.substring(indexRIBbeneficiaire, indexRIBbeneficiaire + 3);
                            long montantOperation = Long.parseLong(line.substring(indexMontant, indexMontant + 15));
                            Operation operation = new Operation(savedLotOperation, referenceOperation, donneur, beneficiaire, montantOperation);
                            operationRepository.save(operation);
                        }
                    } else if (Objects.equals(typeOperation, "013")) {
                        for (String line : data) {
                            String referenceOperation = line.substring(indexReferenceOperation, indexReferenceOperation + 12);
                            String beneficiaire = line.substring(indexRIBbeneficiaire, indexRIBbeneficiaire + 24);
                            long montantOperation = Long.parseLong(line.substring(indexMontant, indexMontant + 15));
                            Operation operation = new Operation(savedLotOperation, referenceOperation, beneficiaire, montantOperation);
                            operationRepository.save(operation);
                        }
                    } else {
                        for (String line : data) {
                            String referenceOperation = line.substring(indexReferenceOperation, indexReferenceOperation + 12);
                            String donneur = line.substring(indexRIBdonneur, indexRIBdonneur + 24);
                            String beneficiaire = line.substring(indexRIBbeneficiaire, indexRIBbeneficiaire + 24);
                            long montantOperation = Long.parseLong(line.substring(indexMontant, indexMontant + 15));
                            Operation operation = new Operation(savedLotOperation, referenceOperation, donneur, beneficiaire, montantOperation);
                            operationRepository.save(operation);
                        }
                    }
                }

            }

        }

    }

    public String[] openFile(String fileName) throws IOException {
        String input = new Scanner(new File(outputDirectoryPath + "/LOT/" + fileName)).useDelimiter("\\Z").next();
        return input.split("\n");
    }
}
