package com.example.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class BatchApplication {
    private final JobLauncher jobLauncher;
    private final Job decouperLot;

    @Autowired
    public BatchApplication(JobLauncher jobLauncher, Job decouperLot) {
        this.jobLauncher = jobLauncher;
        this.decouperLot = decouperLot;
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @Scheduled(fixedRate = 9000000)
    public void scheduleBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(decouperLot, jobParameters);
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
