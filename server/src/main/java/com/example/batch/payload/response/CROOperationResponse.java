package com.example.batch.payload.response;

import com.example.batch.models.CRO;

public class CROOperationResponse {
    private String Id;

    private CRO cro;

    private String RIO;

    private String date;

    private long montant;

    public CROOperationResponse(String Id, CRO cro, String RIO, String date, long montant) {
        this.Id = Id;
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

