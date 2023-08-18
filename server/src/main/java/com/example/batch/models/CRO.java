package com.example.batch.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "cro")
public class CRO {
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

    private String banqueDest;

    private String banqueSource;

    private String numeroLot;

    private String typeOperation;

    private long montant;

    public CRO(String banqueDest, String banqueSource, String numeroLot, String typeOperation, long montant) {
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
