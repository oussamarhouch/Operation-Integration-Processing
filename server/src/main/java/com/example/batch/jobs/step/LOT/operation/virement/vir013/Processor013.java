package com.example.batch.jobs.step.LOT.operation.virement.vir013;

import com.example.batch.jobs.step.LOT.operation.OperationProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor013 extends OperationProcessor {
    public Processor013(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    protected String getOperationType() {
        return "013";
    }

    @Override
    protected int getSubstringStartIndex() {
        return 133;
    }

    @Override
    protected int getSubstringEndIndex() {
        return 148;
    }

    @Override
    protected int getLineLength() {
        return 550;
    }
}
