package com.example.batch.jobs.step.LOT.rejet.rejet120;

import com.example.batch.jobs.step.LOT.rejet.RejetProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor120 extends RejetProcessor {
    public Processor120(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    public String[] process(String data) throws Exception {
        return decouper(data);
    }

}