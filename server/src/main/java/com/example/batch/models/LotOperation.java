package com.example.batch.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "lotOperation")
public class LotOperation {

    @Id
    private String Id;

    @NotBlank
    @Size(max = 20)
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date = String.valueOf(LocalDate.now());

    @NotBlank
    @Size(max = 20)
    @CreatedDate
    @DateTimeFormat(pattern = "hh:mm:ss")
    private String time = String.valueOf(LocalTime.now());

    @DBRef
    private LotMere lotMere;

    @NotBlank
    @Size(max = 3)
    private String typeOperation;

    @NotBlank
    @Size(max = 3)
    private String identifiantBanque;

    @NotBlank
    @Size(max = 3)
    private String identifiantSource;

    @NotBlank
    @Size(max = 3)
    private String numeroLot;

    @NotBlank
    @Size(max = 3)
    private String devise;

    @NotBlank
    private int nombreOperation;

    @NotBlank
    private long montant;


    public LotOperation(String typeOperation, String identifiantBanque, String identifiantSource, String numeroLot, LotMere lotMere, String devise, int nombreOperation, long montant) {
        this.typeOperation = typeOperation;
        this.identifiantBanque = identifiantBanque;
        this.identifiantSource = identifiantSource;
        this.numeroLot = numeroLot;
        this.lotMere = lotMere;
        this.devise = devise;
        this.nombreOperation = nombreOperation;
        this.montant = montant;
    }

    public void setLotMere(LotMere lotMere) {
        this.lotMere = lotMere;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
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

    public LotMere getLotMere() {
        return lotMere;
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
