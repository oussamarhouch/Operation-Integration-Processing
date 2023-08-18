package com.example.batch.jobs.step.LOT.operation.LCN060;

import com.example.batch.jobs.step.LOT.operation.OperationProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor060 extends OperationProcessor {
    public Processor060(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    protected String getOperationType() {
        return "060";
    }

    @Override
    protected int getSubstringStartIndex() {
        return 328;
    }

    @Override
    protected int getSubstringEndIndex() {
        return 343;
    }

    @Override
    protected int getLineLength() {
        return 550;
    }
}
