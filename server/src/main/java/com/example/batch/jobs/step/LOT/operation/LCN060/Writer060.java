package com.example.batch.jobs.step.LOT.operation.LCN060;

import com.example.batch.jobs.step.LOT.operation.OperationWriter;
import com.example.batch.repository.LotMereRepository;
import com.example.batch.repository.LotOperationRepository;
import com.example.batch.repository.OperationRepository;

import java.util.List;

public class Writer060 extends OperationWriter {

    public Writer060(LotOperationRepository lotOperationRepository, LotMereRepository lotMereRepository, OperationRepository operationRepository, String outputDirectoryPath) {
        super(lotOperationRepository, lotMereRepository, operationRepository, outputDirectoryPath);
    }

    @Override
    public void write(List<? extends String[]> list) throws Exception {
        for (String[] item : list) {
            saveLotsToDatabase(item, "060", 3, 16, 215, 328);
        }
    }
}
