package com.example.batch.jobs.step.LOT.rejet.rejet131;

import com.example.batch.jobs.step.LOT.rejet.RejetReader;

import java.io.IOException;

public class Reader131 extends RejetReader {
    public Reader131(String inputDirectoryPath) throws IOException {
        super("*.*.*.131.*.LOT", inputDirectoryPath);
    }
}