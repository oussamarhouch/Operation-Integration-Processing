package com.example.batch.jobs.step.LOT.operation.virement.vir010;

import com.example.batch.jobs.step.LOT.operation.OperationReader;

import java.io.IOException;

public class Reader010 extends OperationReader {
    public Reader010(String directoryPath) throws IOException {
        super(directoryPath, "*.*.*.010.*.LOT");
    }
}
