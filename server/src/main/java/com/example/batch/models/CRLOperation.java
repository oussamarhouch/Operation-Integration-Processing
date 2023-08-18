package com.example.batch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "crl-operation")
public class CRLOperation {
    @Id
    private String Id;

    @DBRef
    private Operation operation;

    @DBRef
    private Rejet rejet;

    @DBRef
    private CRL crl;

    @NotBlank
    private String codeRejetOperation;

    @NotBlank
    private long montant;

    public CRLOperation(Operation operation, Rejet rejet, CRL crl, String codeRejetOperation, long montant) {
        this.operation = operation;
        this.rejet = rejet;
        this.crl = crl;
        this.codeRejetOperation = codeRejetOperation;
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

    public CRL getCrl() {
        return crl;
    }

    public void setCrl(CRL crl) {
        this.crl = crl;
    }

    public String getCodeRejetOperation() {
        return codeRejetOperation;
    }

    public void setCodeRejetOperation(String codeRejetOperation) {
        this.codeRejetOperation = codeRejetOperation;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
}
