package com.example.batch.jobs.step.LOT.operation.virement.vir012;

import com.example.batch.jobs.step.LOT.operation.OperationReader;

import java.io.IOException;

public class Reader012 extends OperationReader {
    public Reader012(String directoryPath) throws IOException {
        super(directoryPath, "*.*.*.012.*.LOT");
    }
}