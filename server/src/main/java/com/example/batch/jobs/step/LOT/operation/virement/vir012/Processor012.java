package com.example.batch.jobs.step.LOT.operation.virement.vir012;

import com.example.batch.jobs.step.LOT.operation.OperationProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor012 extends OperationProcessor {
    public Processor012(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    protected String getOperationType() {
        return "012";
    }

    @Override
    protected int getSubstringStartIndex() {
        return 40;
    }

    @Override
    protected int getSubstringEndIndex() {
        return 55;
    }

    @Override
    protected int getLineLength() {
        return 550;
    }
}
