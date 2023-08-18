package com.example.batch.payload.response;

public class LotOperationResponse {
    private String Id;

    private String date;

    private String time;

    private String lotMereId;

    private String typeOperation;

    private String identifiantBanque;

    private String identifiantSource;

    private String numeroLot;

    private String devise;

    private int nombreOperation;

    private long montant;

    public LotOperationResponse(String id, String date, String time, String lotMereId, String typeOperation,
                                String identifiantBanque, String identifiantSource, String numeroLot, String devise,
                                int nombreOperation, long montant) {
        Id = id;
        this.date = date;
        this.time = time;
        this.lotMereId = lotMereId;
        this.typeOperation = typeOperation;
        this.identifiantBanque = identifiantBanque;
        this.identifiantSource = identifiantSource;
        this.numeroLot = numeroLot;
        this.devise = devise;
        this.nombreOperation = nombreOperation;
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

    public String getLotMereId() {
        return lotMereId;
    }

    public void setLotMereId(String lotMereId) {
        this.lotMereId = lotMereId;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getIdentifiantBanque() {
        return identifiantBanque;
    }

    public void setIdentifiantBanque(String identifiantBanque) {
        this.identifiantBanque = identifiantBanque;
    }

    public String getIdentifiantSource() {
        return identifiantSource;
    }

    public void setIdentifiantSource(String identifiantSource) {
        this.identifiantSource = identifiantSource;
    }

    public String getNumeroLot() {
        return numeroLot;
    }

    public void setNumeroLot(String numeroLot) {
        this.numeroLot = numeroLot;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public int getNombreOperation() {
        return nombreOperation;
    }

    public void setNombreOperation(int nombreOperation) {
        this.nombreOperation = nombreOperation;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }
}
