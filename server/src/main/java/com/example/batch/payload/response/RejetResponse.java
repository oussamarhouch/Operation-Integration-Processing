package com.example.batch.payload.response;

public class RejetResponse {
    private String rejetId;
    private String lotRejetId;

    private String referenceOperation;

    private String motifRejet;

    private String RIO;

    public RejetResponse(String rejetId, String lotRejetId, String referenceOperation, String motifRejet, String RIO) {
        this.rejetId = rejetId;
        this.lotRejetId = lotRejetId;
        this.referenceOperation = referenceOperation;
        this.motifRejet = motifRejet;
        this.RIO = RIO;
    }

    public String getRejetId() {
        return rejetId;
    }

    public void setRejetId(String rejetId) {
        this.rejetId = rejetId;
    }

    public String getLotRejetId() {
        return lotRejetId;
    }

    public void setLotRejetId(String lotRejetId) {
        this.lotRejetId = lotRejetId;
    }

    public String getReferenceOperation() {
        return referenceOperation;
    }

    public void setReferenceOperation(String referenceOperation) {
        this.referenceOperation = referenceOperation;
    }

    public String getMotifRejet() {
        return motifRejet;
    }

    public void setMotifRejet(String motifRejet) {
        this.motifRejet = motifRejet;
    }

    public String getRIO() {
        return RIO;
    }

    public void setRIO(String RIO) {
        this.RIO = RIO;
    }
}
