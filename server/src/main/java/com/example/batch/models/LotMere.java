package com.example.batch.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "lotMere")
public class LotMere {
    @Id
    private String lotMereId;

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

    @NotBlank
    @Size(max = 3)
    private String typeOperation;

    @NotBlank
    private String entete;

    public LotMere(String entete, String typeOperation) {
        this.entete = entete;
        this.typeOperation = typeOperation;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getLotMereId() {
        return lotMereId;
    }

}
