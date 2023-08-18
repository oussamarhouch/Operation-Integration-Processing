package com.example.batch.jobs.config;

import com.example.batch.jobs.flow.Flows;
import com.example.batch.jobs.listener.JobCompletionListener;
import com.example.batch.jobs.listener.JobStartingListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Configuration
public class BatchConfig extends Flows {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Value("${batch.app.inputDirectoryPath}")
    private String inputDirectoryPath;

    final Logger logger = LogManager.getLogger(getClass());

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Job decouperLot() throws IOException {
        return jobBuilderFactory.get("processJob")
                .listener(new JobStartingListener())
                .incrementer(new RunIdIncrementer())
                .start(parallelFlows())
                .next(delayedFlow(flowCRA()))
                .next(delayedFlow(flowCRL()))
                .next(delayedFlow(flowCRO()))
                .end()
                .listener(new JobCompletionListener())
                .build();
    }

    private Flow delayedFlow(Flow flow) {
        Step delayStep = stepBuilderFactory.get(flow.getName() + "_Delayed")
                .tasklet((contribution, chunkContext) -> {
                    try {
                        Thread.sleep(600000);
                    } catch (InterruptedException e) {
                    }
                    return RepeatStatus.FINISHED;
                })
                .build();

        return new FlowBuilder<Flow>(flow.getName())
                .start(delayStep)
                .on("*")
                .to(flow)
                .end();
    }


    public String[] getAllLOTFiles() throws IOException {
        String directoryPath = inputDirectoryPath + "/LOT";
        String filePattern = "*.LOT";

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
        Resource[] resource = resolver.getResources("file:" + directoryPath + "/" + filePattern);

        Set<String> uniqueFiles = new HashSet<>();
        for (Resource res : resource) {
            String filename = Objects.requireNonNull(res.getFilename()).split("\\.")[3];
            uniqueFiles.add(filename);
        }

        return uniqueFiles.toArray(new String[0]);
    }

    @Bean
    public Flow parallelFlows() throws IOException {
        String[] files = getAllLOTFiles();
        List<Flow> flows = new ArrayList<>();
        for (String file : files) {
            switch (file) {
                case "010":
                    flows.add(flow010());
                    break;
                case "012":
                    flows.add(flow012());
                    break;
                case "013":
                    flows.add(flow013());
                    break;
                case "020":
                    flows.add(flow020());
                    break;
                case "031":
                    flows.add(flow031());
                    break;
                case "060":
                    flows.add(flow060());
                    break;
                case "120":
                    flows.add(flow120());
                    break;
                case "131":
                    flows.add(flow131());
                    break;
                case "160":
                    flows.add(flow160());
                    break;
            }
        }
        return new FlowBuilder<SimpleFlow>("parallelFlows")
                .split(taskExecutor())
                .add(flows.toArray(new Flow[0]))
                .build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor("customerInfoThreads-");
        executor.setConcurrencyLimit(4);
        return executor;
    }

    @Bean
    public JobExecutionListener startlistener() {
        return new JobStartingListener();
    }

    @Bean
    public JobExecutionListener endlistener() {
        return new JobCompletionListener();
    }

}
