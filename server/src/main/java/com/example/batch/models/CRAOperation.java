package com.example.batch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "cra-operation")
public class CRAOperation {
    @Id
    private String Id;

    @DBRef
    private Operation operation;

    @DBRef
    private Rejet rejet;

    @DBRef
    private CRA cra;

    @NotBlank
    private String codeRejetOperation;

    @NotBlank
    private String RIO;

    @NotBlank
    private long montant;

    public CRAOperation(Operation operation, Rejet rejet, CRA cra, String codeRejetOperation, String RIO, long montant) {
        this.operation = operation;
        this.rejet = rejet;
        this.cra = cra;
        this.codeRejetOperation = codeRejetOperation;
        this.RIO = RIO;
        this.montant = montant;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Rejet getRejet() {
        return rejet;
    }

    public void setRejet(Rejet rejet) {
        this.rejet = rejet;
    }

    public CRA getCra() {
        return cra;
    }

    public void setCra(CRA cra) {
        this.cra = cra;
    }

    public String getCodeRejetOperation() {
        return codeRejetOperation;
    }

    public void setCodeRejetOperation(String codeRejetOperation) {
        this.codeRejetOperation = codeRejetOperation;
    }

    public String getRIO() {
        return RIO;
    }

    public void setRIO(String RIO) {
        this.RIO = RIO;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
}
