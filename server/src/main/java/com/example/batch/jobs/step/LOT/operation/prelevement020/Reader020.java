package com.example.batch.jobs.step.LOT.operation.prelevement020;

import com.example.batch.jobs.step.LOT.operation.OperationReader;

import java.io.IOException;

public class Reader020 extends OperationReader {
    public Reader020(String directoryPath) throws IOException {
        super(directoryPath, "*.*.*.020.*.LOT");
    }
}