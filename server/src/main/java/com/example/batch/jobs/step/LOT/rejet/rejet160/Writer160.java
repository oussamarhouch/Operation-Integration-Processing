package com.example.batch.jobs.step.LOT.rejet.rejet160;

import com.example.batch.jobs.step.LOT.rejet.RejetWriter;
import com.example.batch.repository.LotMereRepository;
import com.example.batch.repository.LotRejetRepository;
import com.example.batch.repository.RejetRepository;

import java.util.List;

public class Writer160 extends RejetWriter {

    public Writer160(LotRejetRepository lotRejetRepository, LotMereRepository lotMereRepository, RejetRepository rejetRepository, String outputDirectoryPath) {
        super(lotRejetRepository, lotMereRepository, rejetRepository, outputDirectoryPath);
    }

    @Override
    public void write(List<? extends String[]> list) throws Exception {
        for (String[] item : list) {
            saveLotsToDatabase(item, "160");
        }
    }
}