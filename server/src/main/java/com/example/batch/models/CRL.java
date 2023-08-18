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

@Document(collection = "crl")
public class CRL {
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
    private LotRejet lotRejet;

    @DBRef
    private LotOperation lotOperation;

    private String typeOperation;

    @NotBlank
    @Size(max = 2)
    private String codeRejet;

    public CRL(LotOperation lotOperation, LotRejet lotRejet, String codeRejet, String typeOperation) {
        this.lotOperation = lotOperation;
        this.lotRejet = lotRejet;
        this.codeRejet = codeRejet;
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

    public LotRejet getLotRejet() {
        return lotRejet;
    }

    public void setLotRejet(LotRejet lotRejet) {
        this.lotRejet = lotRejet;
    }

    public LotOperation getLotOperation() {
        return lotOperation;
    }

    public void setLotOperation(LotOperation lotOperation) {
        this.lotOperation = lotOperation;
    }

    public String getCodeRejet() {
        return codeRejet;
    }

    public void setCodeRejet(String codeRejet) {
        this.codeRejet = codeRejet;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }
}
