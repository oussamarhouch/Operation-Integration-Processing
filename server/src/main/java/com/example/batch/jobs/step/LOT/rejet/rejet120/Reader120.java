package com.example.batch.jobs.step.LOT.rejet.rejet120;

import com.example.batch.jobs.step.LOT.rejet.RejetReader;

import java.io.IOException;

public class Reader120 extends RejetReader {
    public Reader120(String inputDirectoryPath) throws IOException {
        super("*.*.*.120.*.LOT", inputDirectoryPath);
    }
}