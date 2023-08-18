package com.example.batch.jobs.step.LOT.rejet.rejet160;

import com.example.batch.jobs.step.LOT.rejet.RejetReader;

import java.io.IOException;

public class Reader160 extends RejetReader {
    public Reader160(String inputDirectoryPath) throws IOException {
        super("*.*.*.160.*.LOT", inputDirectoryPath);
    }
}