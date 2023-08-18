package com.example.batch.jobs.step.LOT.rejet;

import com.example.batch.models.LotMere;
import com.example.batch.models.LotRejet;
import com.example.batch.models.Rejet;
import com.example.batch.repository.LotMereRepository;
import com.example.batch.repository.LotRejetRepository;
import com.example.batch.repository.RejetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public abstract class RejetWriter implements ItemWriter<String[]> {

    @Autowired
    private final LotMereRepository lotMereRepository;

    @Autowired
    private final LotRejetRepository lotRejetRepository;

    @Autowired
    private final RejetRepository rejetRepository;

    protected final Logger logger;

    private String outputDirectoryPath;

    public RejetWriter(LotRejetRepository lotRejetRepository, LotMereRepository lotMereRepository, RejetRepository rejetRepository, String outputDirectoryPath) {
        this.lotRejetRepository = lotRejetRepository;
        this.lotMereRepository = lotMereRepository;
        this.rejetRepository = rejetRepository;
        this.logger = LogManager.getLogger(getClass());
        this.outputDirectoryPath = outputDirectoryPath;
    }

    public String[] entete(String line, String operationType) {
        String id = line.substring(0, 4);
        String bankId = line.substring(4, 7);
        String sourceId = line.substring(7, 10);
        String lotNumber = line.substring(10, 13);
        String devise = line.substring(13, 16);
        String operationNumber = line.substring(16, 20);
        return new String[]{id, bankId, sourceId, lotNumber, operationType, devise, operationNumber};
    }

    public void saveLotsToDatabase(String[] item, String typeOperation) throws IOException {
        ObjectId idLotMere = new ObjectId(item[0]);
        LotMere lotMere = lotMereRepository.findByLotMereId(idLotMere).orElse(null);

        String[] files = Arrays.copyOfRange(item, 1, item.length);

        for (String fileName : files) {
            String[] input = openFile(fileName);
            String firstline = input[0];
            String[] entete = entete(firstline, typeOperation);
            String identifiantBanque = entete[1];
            String identifiantSource = entete[2];
            String numeroLot = entete[3];
            String devise = entete[5];
            int nombreRejet = Integer.parseInt(entete[6]);
            String[] data = Arrays.copyOfRange(input, 1, input.length);


            if (lotRejetRepository.existsRejetByIdentifiantBanqueAndIdentifiantSourceAndLotMereAndNumeroLotAndDeviseAndTypeOperationAndDate(
                    identifiantBanque, identifiantSource, lotMere, numeroLot, devise, typeOperation, String.valueOf(LocalDate.now()))) {
                logger.error("Cette opération existe déja");
            } else {
                LotRejet lotRejet = new LotRejet(typeOperation, identifiantBanque, identifiantSource, numeroLot, lotMere, devise, nombreRejet);
                LotRejet savedLotRejet = lotRejetRepository.save(lotRejet);
                logger.info("Lot " + fileName + " registered successfully!");
                for (String line : data) {
                    String referenceOperation = line.substring(6, 18);
                    String motifRejet = line.substring(3, 6);
                    String RIO = line.substring(21, 53);
                    Rejet rejet = new Rejet(savedLotRejet, referenceOperation, motifRejet, RIO);
                    rejetRepository.save(rejet);
                }
            }
        }

    }

    public String[] openFile(String fileName) throws IOException {
        String input = new Scanner(new File(outputDirectoryPath + "/LOT/" + fileName)).useDelimiter("\\Z").next();
        return input.split("\n");
    }

}
