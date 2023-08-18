package com.example.batch.jobs.step.LOT.operation.virement.vir010;

import com.example.batch.jobs.step.LOT.operation.OperationProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor010 extends OperationProcessor {
    public Processor010(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    protected String getOperationType() {
        return "010";
    }

    @Override
    protected int getSubstringStartIndex() {
        return 262;
    }

    @Override
    protected int getSubstringEndIndex() {
        return 277;
    }

    @Override
    protected int getLineLength() {
        return 550;
    }
}
