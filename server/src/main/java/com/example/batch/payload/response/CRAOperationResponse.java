package com.example.batch.payload.response;

import com.example.batch.models.CRA;
import com.example.batch.models.Operation;
import com.example.batch.models.Rejet;

public class CRAOperationResponse {
    private String Id;

    private Operation operation;

    private Rejet rejet;

    private CRA cra;

    private String codeRejetOperation;

    private String RIO;

    private long montant;

    public CRAOperationResponse(String Id, Operation operation, Rejet rejet, CRA cra, String codeRejetOperation, String RIO, long montant) {
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
