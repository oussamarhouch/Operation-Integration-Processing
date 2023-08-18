package com.example.batch.jobs.step.LOT.operation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class OperationReader implements ItemReader<String> {

    private Logger logger;
    private Iterator<String> fileIterator;

    public OperationReader(String directoryPath, String filePattern) throws IOException {
        this.logger = LogManager.getLogger(getClass());
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new IOException("Failed to create the directory: " + directoryPath);
            }
        }

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("file:" + directoryPath + "/LOT/" + filePattern);
        List<String> files = new ArrayList<>();
        for (Resource res : resources) {
            files.add(res.getFilename());
        }

        fileIterator = files.iterator();
    }

    @Override
    public String read() {
        if (fileIterator.hasNext()) {
            String fileName = fileIterator.next();
            fileIterator.remove();
            return fileName;
        }
        return null;
    }

    public boolean hasNext() {
        return fileIterator.hasNext();
    }

    public String next() {
        return fileIterator.next();
    }
}
