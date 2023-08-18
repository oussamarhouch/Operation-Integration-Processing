package com.example.batch.payload.response;

public class CROResponse {
    private String Id;

    private String date;

    private String time;

    private String banqueDest;

    private String banqueSource;

    private String numeroLot;

    private String typeOperation;

    private long montant;

    public CROResponse(String id, String date, String time, String banqueDest, String banqueSource, String numeroLot, String typeOperation, long montant) {
        this.Id = Id;
        this.date = date;
        this.time = time;
        this.banqueDest = banqueDest;
        this.banqueSource = banqueSource;
        this.numeroLot = numeroLot;
        this.typeOperation = typeOperation;
        this.montant = montant;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBanqueDest() {
        return banqueDest;
    }

    public void setBanqueDest(String banqueDest) {
        this.banqueDest = banqueDest;
    }

    public String getBanqueSource() {
        return banqueSource;
    }

    public void setBanqueSource(String banqueSource) {
        this.banqueSource = banqueSource;
    }

    public String getNumeroLot() {
        return numeroLot;
    }

    public void setNumeroLot(String numeroLot) {
        this.numeroLot = numeroLot;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
}
