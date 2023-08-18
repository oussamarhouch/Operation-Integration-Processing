package com.example.batch.jobs.step.LOT.operation.cheque031;

import com.example.batch.jobs.step.LOT.operation.OperationReader;

import java.io.IOException;

public class Reader031 extends OperationReader {
    public Reader031(String directoryPath) throws IOException {
        super(directoryPath, "*.*.*.031.*.LOT");
    }
}