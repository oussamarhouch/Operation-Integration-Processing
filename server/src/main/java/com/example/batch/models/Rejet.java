package com.example.batch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rejet")
public class Rejet {
    @Id
    private String Id;

    @DBRef
    private LotRejet lotRejet;

    private String referenceOperation;

    private String motifRejet;

    private String RIO;

    public Rejet(LotRejet lotRejet, String referenceOperation, String motifRejet, String RIO) {
        this.lotRejet = lotRejet;
        this.referenceOperation = referenceOperation;
        this.motifRejet = motifRejet;
        this.RIO = RIO;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public LotRejet getLotRejet() {
        return lotRejet;
    }

    public void setLotRejet(LotRejet lotRejet) {
        this.lotRejet = lotRejet;
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
