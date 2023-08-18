package com.example.batch.payload.response;

public class OperationResponse {
    private String operationId;
    private String lotOperationId;
    private String referenceOperation;
    private String donneur;
    private String beneficiaire;
    private long montant;

    public OperationResponse(String operationId, String lotOperationId, String referenceOperation, String donneur, String beneficiaire, long montant) {
        
        this.operationId = operationId;
        this.lotOperationId = lotOperationId;
        this.referenceOperation = referenceOperation;
        this.donneur = donneur;
        this.beneficiaire = beneficiaire;
        this.montant = montant;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getLotOperationId() {
        return lotOperationId;
    }

    public void setLotOperationId(String lotOperationId) {
        this.lotOperationId = lotOperationId;
    }

    public String getReferenceOperation() {
        return referenceOperation;
    }

    public void setReferenceOperation(String referenceOperation) {
        this.referenceOperation = referenceOperation;
    }

    public String getDonneur() {
        return donneur;
    }

    public void setDonneur(String donneur) {
        this.donneur = donneur;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
}
