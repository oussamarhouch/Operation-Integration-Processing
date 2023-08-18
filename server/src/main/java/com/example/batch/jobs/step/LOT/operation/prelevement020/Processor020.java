package com.example.batch.jobs.step.LOT.operation.prelevement020;

import com.example.batch.jobs.step.LOT.operation.OperationProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor020 extends OperationProcessor {
    public Processor020(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    protected String getOperationType() {
        return "020";
    }

    @Override
    protected int getSubstringStartIndex() {
        return 247;
    }

    @Override
    protected int getSubstringEndIndex() {
        return 262;
    }

    @Override
    protected int getLineLength() {
        return 550;
    }
}
