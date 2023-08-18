package com.example.batch.jobs.step.CRO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ReaderCRO implements ItemReader<String> {
    private final Iterator<String> fileIterator;
    protected final Logger logger;

    public ReaderCRO(String inputDirectoryPath) throws IOException {
        this.logger = LogManager.getLogger(getClass());

        String directoryPath = inputDirectoryPath + "/CRO";
        String filePattern = "*.CRO";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            try {
                if (!created) {
                    throw new IOException("Failed to create the directory: " + directoryPath);
                }
            } catch (IOException e) {
                logger.error("Error occurred: " + e.getMessage(), e);
            }
        }

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("file:" + directoryPath + "/" + filePattern);

        Set<String> uniqueFiles = new HashSet<>();
        for (Resource res : resources) {
            uniqueFiles.add(String.valueOf(res.getFilename()));
        }

        fileIterator = uniqueFiles.iterator();
    }

    @Override
    public String read() {
        if (fileIterator.hasNext()) {
            return fileIterator.next();
        }
        return null;
    }
}
