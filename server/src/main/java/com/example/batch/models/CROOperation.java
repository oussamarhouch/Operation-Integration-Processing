package com.example.batch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cro-operation")
public class CROOperation {
    @Id
    private String Id;

    @DBRef
    private CRO cro;

    private String RIO;

    private String date;

    private long montant;

    public CROOperation(CRO cro, String RIO, String date, long montant) {
        this.cro = cro;
        this.RIO = RIO;
        this.date = date;
        this.montant = montant;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public CRO getCro() {
        return cro;
    }

    public void setCro(CRO cro) {
        this.cro = cro;
    }

    public String getRIO() {
        return RIO;
    }

    public void setRIO(String RIO) {
        this.RIO = RIO;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
}
