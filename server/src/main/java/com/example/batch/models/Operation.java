package com.example.batch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "operation")
public class Operation {
    @Id
    private String Id;

    @DBRef
    private LotOperation lotOperation;

    @NotBlank
    private String referenceOperation;

    private String donneur;

    private String beneficiaire;

    private long montant;

    public Operation(LotOperation lotOperation, String referenceOperation, String donneur, String beneficiaire, long montant) {
        this.lotOperation = lotOperation;
        this.referenceOperation = referenceOperation;
        this.donneur = donneur;
        this.beneficiaire = beneficiaire;
        this.montant = montant;
    }

    public Operation(LotOperation lotOperation, String referenceOperation, String beneficiaire, long montant) {
        this.lotOperation = lotOperation;
        this.referenceOperation = referenceOperation;
        this.beneficiaire = beneficiaire;
        this.montant = montant;
    }

    public Operation() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public LotOperation getLotOperation() {
        return lotOperation;
    }

    public void setLotOperation(LotOperation lotOperation) {
        this.lotOperation = lotOperation;
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

