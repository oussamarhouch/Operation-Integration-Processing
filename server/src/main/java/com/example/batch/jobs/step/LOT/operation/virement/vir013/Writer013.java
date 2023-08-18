package com.example.batch.jobs.step.LOT.operation.virement.vir013;

import com.example.batch.jobs.step.LOT.operation.OperationWriter;
import com.example.batch.repository.LotMereRepository;
import com.example.batch.repository.LotOperationRepository;
import com.example.batch.repository.OperationRepository;

import java.util.List;

public class Writer013 extends OperationWriter {

    public Writer013(LotOperationRepository lotOperationRepository, LotMereRepository lotMereRepository, OperationRepository operationRepository, String outputDirectoryPath) {
        super(lotOperationRepository, lotMereRepository, operationRepository, outputDirectoryPath);
    }

    @Override
    public void write(List<? extends String[]> list) throws Exception {
        for (String[] item : list) {
            saveLotsToDatabase(item, "013", 4, 0, 20, 133);
        }
    }
}