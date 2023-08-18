package com.example.batch.jobs.step.LOT.operation.virement.vir013;

import com.example.batch.jobs.step.LOT.operation.OperationReader;

import java.io.IOException;

public class Reader013 extends OperationReader {
    public Reader013(String directoryPath) throws IOException {
        super(directoryPath, "*.*.*.013.*.LOT");
    }
}