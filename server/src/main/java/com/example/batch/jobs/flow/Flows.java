package com.example.batch.jobs.flow;

import com.example.batch.jobs.step.Steps;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Flows extends Steps {

    @Bean
    public Flow flow010() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 010")
                .start(Step010())
                .build();
    }

    @Bean
    public Flow flow012() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 012")
                .start(Step012())
                .build();
    }

    @Bean
    public Flow flow013() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 013")
                .start(Step013())
                .build();
    }

    @Bean
    public Flow flow020() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 020")
                .start(Step020())
                .build();
    }

    @Bean
    public Flow flow031() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 031")
                .start(Step031())
                .build();
    }

    @Bean
    public Flow flow060() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 060")
                .start(Step060())
                .build();
    }

    @Bean
    public Flow flow120() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 120")
                .start(Step120())
                .build();
    }

    @Bean
    public Flow flow131() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 131")
                .start(Step131())
                .build();
    }

    @Bean
    public Flow flow160() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du lot 160")
                .start(Step160())
                .build();
    }

    @Bean
    public Flow flowCRL() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du CRL")
                .start(StepCRL())
                .build();
    }

    @Bean
    public Flow flowCRA() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du CRA")
                .start(StepCRA())
                .build();
    }

    @Bean
    public Flow flowCRO() throws IOException {
        return new FlowBuilder<SimpleFlow>("flow du CRO")
                .start(StepCRO())
                .build();
    }
}
