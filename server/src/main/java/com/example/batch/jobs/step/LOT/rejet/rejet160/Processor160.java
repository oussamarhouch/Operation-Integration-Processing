package com.example.batch.jobs.step.LOT.rejet.rejet160;

import com.example.batch.jobs.step.LOT.rejet.RejetProcessor;
import com.example.batch.repository.LotMereRepository;

public class Processor160 extends RejetProcessor {
    public Processor160(LotMereRepository lotsMereRepository, String inputDirectoryPath, String outputDirectoryPath) {
        super(lotsMereRepository, inputDirectoryPath, outputDirectoryPath);
    }

    @Override
    public String[] process(String data) throws Exception {
        return decouper(data);
    }


}