package com.example.batch.payload.response;

import com.example.batch.models.LotOperation;
import com.example.batch.models.LotRejet;

public class CRAResponse {
    private String Id;

    private String date;

    private String time;

    private LotRejet lotRejet;

    private LotOperation lotOperation;

    private String codeRejet;

    public CRAResponse(String id, String date, String time, LotRejet lotRejet, LotOperation lotOperation, String codeRejet) {
        Id = id;
        this.date = date;
        this.time = time;
        this.lotRejet = lotRejet;
        this.lotOperation = lotOperation;
        this.codeRejet = codeRejet;
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
}
