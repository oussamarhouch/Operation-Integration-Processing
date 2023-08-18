package com.example.batch.controllers;

import com.example.batch.models.CRO;
import com.example.batch.models.CROOperation;
import com.example.batch.payload.response.CROOperationResponse;
import com.example.batch.payload.response.CROResponse;
import com.example.batch.repository.CROOperationRepository;
import com.example.batch.repository.CRORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cro")
public class CROController {
    @Autowired
    CRORepository croRepository;

    @Autowired
    CROOperationRepository croOperationRepository;

    String[] operationTypes = {"010", "012", "013", "020", "031", "060"};
    String[] rejetTypes = {"120", "131", "160"};

    @GetMapping("/{id}")
    public ResponseEntity<?> getCRObyId(@PathVariable String id) {
        Optional<CRO> cro = croRepository.findById(id);
        if (cro.isPresent()) {
            return ResponseEntity.ok(new CROResponse(cro.get().getId(), cro.get().getDate(), cro.get().getTime(),
                    cro.get().getBanqueSource(), cro.get().getBanqueDest(), cro.get().getNumeroLot(),
                    cro.get().getTypeOperation(), cro.get().getMontant()));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{typeOperation}/{date}")
    public ResponseEntity<?> getCRO(@PathVariable String typeOperation, @PathVariable String date) {
        String dateStr = date.substring(4, 8) + "-" + date.substring(2, 4) + "-" + date.substring(0, 2);
        List<CROResponse> croResponses = new ArrayList<>();
        List<CRO> cros = croRepository.findByTypeOperationAndDate(typeOperation, dateStr);

        for (CRO cro : cros) {
            CROResponse croResponse = new CROResponse(cro.getId(), cro.getDate(), cro.getTime(),
                    cro.getBanqueSource(), cro.getBanqueDest(), cro.getNumeroLot(), cro.getTypeOperation(), cro.getMontant());

            croResponses.add(croResponse);
        }
        if (!croResponses.isEmpty()) {
            return ResponseEntity.ok(croResponses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("operation/{typeOperation}/{date}")
    public ResponseEntity<?> getCROOperations(@PathVariable String typeOperation, @PathVariable String date) {
        if (Arrays.asList(operationTypes).contains(typeOperation)) {
            String formattedDate = formatDate(date);
            List<CROOperationResponse> croOperationResponses = new ArrayList<>();
            List<CRO> cros = croRepository.findByTypeOperationAndDate(typeOperation, formattedDate);

            for (CRO cro : cros) {
                List<CROOperation> croOperations = croOperationRepository.findByCro(cro);
                for (CROOperation croOperation : croOperations) {

                    CROOperationResponse croOperationResponse = new CROOperationResponse(
                            croOperation.getId(),
                            croOperation.getCro(),
                            croOperation.getRIO(),
                            croOperation.getDate(),
                            croOperation.getMontant()

                    );

                    croOperationResponses.add(croOperationResponse);
                }
            }
            if (!croOperationResponses.isEmpty()) {
                return ResponseEntity.ok(croOperationResponses);
            } else {
                throw new RuntimeException("Erreur: Operations non trouvées pour le type " + typeOperation + " et la date " + formattedDate);
            }
        } else if (Arrays.asList(rejetTypes).contains(typeOperation)) {
            String formattedDate = formatDate(date);
            List<CROOperationResponse> croOperationResponses = new ArrayList<>();
            List<CRO> cros = croRepository.findByTypeOperationAndDate(typeOperation, formattedDate);

            for (CRO cro : cros) {
                List<CROOperation> croOperations = croOperationRepository.findByCro(cro);
                for (CROOperation croOperation : croOperations) {

                    CROOperationResponse croOperationResponse = new CROOperationResponse(
                            croOperation.getId(),
                            croOperation.getCro(),
                            croOperation.getRIO(),
                            croOperation.getDate(),
                            0

                    );

                    croOperationResponses.add(croOperationResponse);
                }
            }
            if (!croOperationResponses.isEmpty()) {
                return ResponseEntity.ok(croOperationResponses);
            } else {
                throw new RuntimeException("Erreur: Operations non trouvées pour le type " + typeOperation + " et la date " + formattedDate);
            }
        } else {
            return ResponseEntity.notFound().build();
        }


    }


    private String formatDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        return outputFormatter.format(parsedDate);
    }
}
