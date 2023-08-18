package com.example.batch.payload.response;

import com.example.batch.models.CRL;
import com.example.batch.models.Operation;
import com.example.batch.models.Rejet;

public class CRLOperationResponse {
    private String Id;

    private Operation operation;

    private Rejet rejet;

    private CRL crl;

    private String codeRejetOperation;


    private long montant;

    public CRLOperationResponse(String Id, Operation operation, Rejet rejet, CRL crl, String codeRejetOperation, long montant) {
        this.Id = Id;
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
