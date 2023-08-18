package com.example.batch.jobs.step.LOT.operation.LCN060;

import com.example.batch.jobs.step.LOT.operation.OperationReader;

import java.io.IOException;

public class Reader060 extends OperationReader {
    public Reader060(String directoryPath) throws IOException {
        super(directoryPath, "*.*.*.060.*.LOT");
    }
}
