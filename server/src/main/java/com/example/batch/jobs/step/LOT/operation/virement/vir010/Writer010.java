package com.example.batch.jobs.step.LOT.operation.virement.vir010;

import com.example.batch.jobs.step.LOT.operation.OperationWriter;
import com.example.batch.repository.LotMereRepository;
import com.example.batch.repository.LotOperationRepository;
import com.example.batch.repository.OperationRepository;

import java.util.List;

public class Writer010 extends OperationWriter {

    public Writer010(LotOperationRepository lotOperationRepository, LotMereRepository lotMereRepository, OperationRepository operationRepository, String outputDirectoryPath) {
        super(lotOperationRepository, lotMereRepository, operationRepository, outputDirectoryPath);
    }

    @Override
    public void write(List<? extends String[]> list) throws Exception {
        for (String[] item : list) {
            saveLotsToDatabase(item, "010", 4, 17, 149, 262);
        }
    }
}
