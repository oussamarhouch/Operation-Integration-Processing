package com.example.batch.jobs.step.LOT.operation.cheque031;

import com.example.batch.jobs.step.LOT.operation.OperationProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor031 extends OperationProcessor {

    public Processor031(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    protected String getOperationType() {
        return "031";
    }

    @Override
    protected int getSubstringStartIndex() {
        return 250;
    }

    @Override
    protected int getSubstringEndIndex() {
        return 265;
    }

    @Override
    protected int getLineLength() {
        return 550;
    }
}