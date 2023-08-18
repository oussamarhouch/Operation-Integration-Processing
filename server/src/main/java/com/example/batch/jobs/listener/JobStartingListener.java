package com.example.batch.jobs.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobStartingListener implements JobExecutionListener {
    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void beforeJob(JobExecution jobExecution) {
        logger.info("\n\n " +
                "**************************************************************************** Début du Processing ****************************************************************************" +
                "\n\n");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }

}
