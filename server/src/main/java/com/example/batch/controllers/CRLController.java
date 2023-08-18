package com.example.batch.controllers;

import com.example.batch.models.CRL;
import com.example.batch.models.CRLOperation;
import com.example.batch.payload.response.CRLOperationResponse;
import com.example.batch.payload.response.CRLResponse;
import com.example.batch.repository.CRLOperationRepository;
import com.example.batch.repository.CRLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/crl")
public class CRLController {

    private final CRLRepository crlRepository;
    private final CRLOperationRepository crlOperationRepository;

    String[] operationTypes = {"010", "012", "013", "020", "031", "060"};
    String[] rejetTypes = {"120", "131", "160"};

    @Autowired
    public CRLController(CRLRepository crlRepository, CRLOperationRepository crlOperationRepository) {
        this.crlRepository = crlRepository;
        this.crlOperationRepository = crlOperationRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCRLbyId(@PathVariable String id) {
        Optional<CRL> crlOptional = crlRepository.findById(id);
        if (crlOptional.isPresent()) {
            CRL crl = crlOptional.get();
            CRLResponse crlResponse = new CRLResponse(crl.getId(), crl.getDate(), crl.getTime(),
                    crl.getLotRejet(), crl.getLotOperation(), crl.getCodeRejet());
            return ResponseEntity.ok(crlResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{typeOperation}/{date}")
    public ResponseEntity<?> getCRL(@PathVariable String typeOperation, @PathVariable String date) {
        String dateStr = date.substring(4, 8) + "-" + date.substring(2, 4) + "-" + date.substring(0, 2);
        List<CRLResponse> crlResponses = new ArrayList<>();
        List<CRL> crls = crlRepository.findByTypeOperationAndDate(typeOperation, dateStr);

        for (CRL crl : crls) {
            CRLResponse crlResponse = new CRLResponse(crl.getId(), crl.getDate(), crl.getTime(),
                    crl.getLotRejet(), crl.getLotOperation(), crl.getCodeRejet());

            crlResponses.add(crlResponse);
        }
        if (!crlResponses.isEmpty()) {
            return ResponseEntity.ok(crlResponses);
        } else {
            throw new RuntimeException("Erreur: Lots Operations non trouvés pour le type " + typeOperation + " et la date " + dateStr);
        }

    }

    @GetMapping("operation/{typeOperation}/{date}")
    public ResponseEntity<?> getCRLOperations(@PathVariable String typeOperation, @PathVariable String date) {
        String formattedDate = formatDate(date);

        List<CRLOperationResponse> crlOperationResponses = new ArrayList<>();
        List<CRL> crls = crlRepository.findByTypeOperationAndDate(typeOperation, formattedDate);
        for (CRL crl : crls) {
            List<CRLOperation> crlOperations = crlOperationRepository.findByCrl(crl);
            for (CRLOperation crlOperation : crlOperations) {
                CRLOperationResponse crlOperationResponse = new CRLOperationResponse(crlOperation.getId(), crlOperation.getOperation(), crlOperation.getRejet()
                        , crlOperation.getCrl(), crlOperation.getCodeRejetOperation(), crlOperation.getMontant());
                crlOperationResponses.add(crlOperationResponse);
            }
        }

        return crlOperationResponses.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(crlOperationResponses);
    }

    private String formatDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        return outputFormatter.format(parsedDate);
    }
}