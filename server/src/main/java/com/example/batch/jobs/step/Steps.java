package com.example.batch.jobs.step;

import com.example.batch.jobs.step.CRA.ProcessorCRA;
import com.example.batch.jobs.step.CRA.ReaderCRA;
import com.example.batch.jobs.step.CRA.WriterCRA;
import com.example.batch.jobs.step.CRL.ProcessorCRL;
import com.example.batch.jobs.step.CRL.ReaderCRL;
import com.example.batch.jobs.step.CRL.WriterCRL;
import com.example.batch.jobs.step.CRO.ProcessorCRO;
import com.example.batch.jobs.step.CRO.ReaderCRO;
import com.example.batch.jobs.step.CRO.WriterCRO;
import com.example.batch.jobs.step.LOT.operation.LCN060.Processor060;
import com.example.batch.jobs.step.LOT.operation.LCN060.Reader060;
import com.example.batch.jobs.step.LOT.operation.LCN060.Writer060;
import com.example.batch.jobs.step.LOT.operation.cheque031.Processor031;
import com.example.batch.jobs.step.LOT.operation.cheque031.Reader031;
import com.example.batch.jobs.step.LOT.operation.cheque031.Writer031;
import com.example.batch.jobs.step.LOT.operation.prelevement020.Processor020;
import com.example.batch.jobs.step.LOT.operation.prelevement020.Reader020;
import com.example.batch.jobs.step.LOT.operation.prelevement020.Writer020;
import com.example.batch.jobs.step.LOT.operation.virement.vir010.Processor010;
import com.example.batch.jobs.step.LOT.operation.virement.vir010.Reader010;
import com.example.batch.jobs.step.LOT.operation.virement.vir010.Writer010;
import com.example.batch.jobs.step.LOT.operation.virement.vir012.Processor012;
import com.example.batch.jobs.step.LOT.operation.virement.vir012.Reader012;
import com.example.batch.jobs.step.LOT.operation.virement.vir012.Writer012;
import com.example.batch.jobs.step.LOT.operation.virement.vir013.Processor013;
import com.example.batch.jobs.step.LOT.operation.virement.vir013.Reader013;
import com.example.batch.jobs.step.LOT.operation.virement.vir013.Writer013;
import com.example.batch.jobs.step.LOT.rejet.rejet120.Processor120;
import com.example.batch.jobs.step.LOT.rejet.rejet120.Reader120;
import com.example.batch.jobs.step.LOT.rejet.rejet120.Writer120;
import com.example.batch.jobs.step.LOT.rejet.rejet131.Processor131;
import com.example.batch.jobs.step.LOT.rejet.rejet131.Reader131;
import com.example.batch.jobs.step.LOT.rejet.rejet131.Writer131;
import com.example.batch.jobs.step.LOT.rejet.rejet160.Processor160;
import com.example.batch.jobs.step.LOT.rejet.rejet160.Reader160;
import com.example.batch.jobs.step.LOT.rejet.rejet160.Writer160;
import com.example.batch.repository.*;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class Steps {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private LotMereRepository lotsMereRepository;

    @Autowired
    public LotOperationRepository lotOperationRepository;

    @Autowired
    public LotRejetRepository lotRejetRepository;

    @Autowired
    public OperationRepository operationRepository;

    @Autowired
    public RejetRepository rejetRepository;

    @Autowired
    public CRLRepository crlRepository;

    @Autowired
    public CRLOperationRepository crlOperationRepository;

    @Autowired
    public CRARepository craRepository;

    @Autowired
    public CRAOperationRepository craOperationRepository;

    @Autowired
    public CRORepository croRepository;

    @Autowired
    public CROOperationRepository croOperationRepository;

    @Value("${batch.app.inputDirectoryPath}")
    public String inputDirectoryPath;

    @Value("${batch.app.outputDirectoryPath}")
    public String outputDirectoryPath;

    @Bean
    public Step Step010() throws IOException {
        return stepBuilderFactory.get("LOT 010:").<String, String[]>chunk(1)
                .reader(new Reader010(inputDirectoryPath)).processor(new Processor010(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer010(lotOperationRepository, lotsMereRepository, operationRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step012() throws IOException {
        return stepBuilderFactory.get("LOT 012:").<String, String[]>chunk(1)
                .reader(new Reader012(inputDirectoryPath)).processor(new Processor012(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer012(lotOperationRepository, lotsMereRepository, operationRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step013() throws IOException {
        return stepBuilderFactory.get("LOT 013:").<String, String[]>chunk(1)
                .reader(new Reader013(inputDirectoryPath)).processor(new Processor013(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer013(lotOperationRepository, lotsMereRepository, operationRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step020() throws IOException {
        return stepBuilderFactory.get("LOT 020:").<String, String[]>chunk(1)
                .reader(new Reader020(inputDirectoryPath)).processor(new Processor020(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer020(lotOperationRepository, lotsMereRepository, operationRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step031() throws IOException {
        return stepBuilderFactory.get("LOT 031:").<String, String[]>chunk(1)
                .reader(new Reader031(inputDirectoryPath)).processor(new Processor031(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer031(lotOperationRepository, lotsMereRepository, operationRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step060() throws IOException {
        return stepBuilderFactory.get("LOT 060:").<String, String[]>chunk(1)
                .reader(new Reader060(inputDirectoryPath)).processor(new Processor060(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer060(lotOperationRepository, lotsMereRepository, operationRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step120() throws IOException {
        return stepBuilderFactory.get("LOT 120:").<String, String[]>chunk(1)
                .reader(new Reader120(inputDirectoryPath)).processor(new Processor120(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer120(lotRejetRepository, lotsMereRepository, rejetRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step131() throws IOException {
        return stepBuilderFactory.get("LOT 131:").<String, String[]>chunk(1)
                .reader(new Reader131(inputDirectoryPath)).processor(new Processor131(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer131(lotRejetRepository, lotsMereRepository, rejetRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step Step160() throws IOException {
        return stepBuilderFactory.get("LOT 160:").<String, String[]>chunk(1)
                .reader(new Reader160(inputDirectoryPath)).processor(new Processor160(lotsMereRepository, inputDirectoryPath, outputDirectoryPath))
                .writer(new Writer160(lotRejetRepository, lotsMereRepository, rejetRepository, outputDirectoryPath)).build();
    }

    @Bean
    public Step StepCRL() throws IOException {
        return stepBuilderFactory.get("Intégration CRL").<String, String[]>chunk(1)
                .reader(new ReaderCRL(inputDirectoryPath)).processor(new ProcessorCRL(lotOperationRepository, lotRejetRepository,
                        crlRepository, operationRepository, rejetRepository, crlOperationRepository, inputDirectoryPath))
                .writer(new WriterCRL()).build();
    }

    @Bean
    public Step StepCRA() throws IOException {
        return stepBuilderFactory.get("Intégration CRA").<String, String[]>chunk(1)
                .reader(new ReaderCRA(inputDirectoryPath)).processor(new ProcessorCRA(lotOperationRepository, lotRejetRepository,
                        craRepository, operationRepository, rejetRepository, craOperationRepository, inputDirectoryPath))
                .writer(new WriterCRA()).build();
    }

    @Bean
    public Step StepCRO() throws IOException {
        return stepBuilderFactory.get("Intégration CRO").<String, String[]>chunk(1)
                .reader(new ReaderCRO(inputDirectoryPath)).processor(new ProcessorCRO(inputDirectoryPath, croRepository, croOperationRepository))
                .writer(new WriterCRO()).build();
    }
}
