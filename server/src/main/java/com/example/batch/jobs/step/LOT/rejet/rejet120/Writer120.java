package com.example.batch.jobs.step.LOT.rejet.rejet120;

import com.example.batch.jobs.step.LOT.rejet.RejetWriter;
import com.example.batch.repository.LotMereRepository;
import com.example.batch.repository.LotRejetRepository;
import com.example.batch.repository.RejetRepository;

import java.util.List;

public class Writer120 extends RejetWriter {

    public Writer120(LotRejetRepository lotRejetRepository, LotMereRepository lotMereRepository, RejetRepository rejetRepository, String outputDirectoryPath) {
        super(lotRejetRepository, lotMereRepository, rejetRepository, outputDirectoryPath);
    }

    @Override
    public void write(List<? extends String[]> list) throws Exception {
        for (String[] item : list) {
            saveLotsToDatabase(item, "120");
        }
    }
}

