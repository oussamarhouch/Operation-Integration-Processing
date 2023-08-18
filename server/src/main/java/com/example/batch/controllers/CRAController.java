package com.example.batch.controllers;

import com.example.batch.models.CRA;
import com.example.batch.models.CRAOperation;
import com.example.batch.payload.response.CRAOperationResponse;
import com.example.batch.payload.response.CRAResponse;
import com.example.batch.repository.CRAOperationRepository;
import com.example.batch.repository.CRARepository;
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
@RequestMapping("/api/cra")
public class CRAController {

    @Autowired
    CRARepository craRepository;

    @Autowired
    CRAOperationRepository craOperationRepository;

    String[] operationTypes = {"010", "012", "013", "020", "031", "060"};
    String[] rejetTypes = {"120", "131", "160"};

    @GetMapping("/{id}")
    public ResponseEntity<?> getCRAbyId(@PathVariable String id) {
        Optional<CRA> cra = craRepository.findById(id);
        if (cra.isPresent()) {
            return ResponseEntity.ok(new CRAResponse(cra.get().getId(), cra.get().getDate(), cra.get().getTime(), cra.get().getLotRejet(), cra.get().getLotOperation(), cra.get().getCodeRejet()));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{typeOperation}/{date}")
    public ResponseEntity<?> getCRA(@PathVariable String typeOperation, @PathVariable String date) {
        String dateStr = date.substring(4, 8) + "-" + date.substring(2, 4) + "-" + date.substring(0, 2);
        List<CRAResponse> craResponses = new ArrayList<>();
        List<CRA> cras = craRepository.findByTypeOperationAndDate(typeOperation, dateStr);

        for (CRA cra : cras) {
            CRAResponse craResponse = new CRAResponse(cra.getId(), cra.getDate(), cra.getTime(),
                    cra.getLotRejet(), cra.getLotOperation(), cra.getCodeRejet());

            craResponses.add(craResponse);
        }
        if (!craResponses.isEmpty()) {
            return ResponseEntity.ok(craResponses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("operation/{typeOperation}/{date}")
    public ResponseEntity<?> getCRAOperations(@PathVariable String typeOperation, @PathVariable String date) {
        if (Arrays.asList(operationTypes).contains(typeOperation)) {
            String formattedDate = formatDate(date);
            List<CRAOperationResponse> craOperationResponses = new ArrayList<>();
            List<CRA> cras = craRepository.findByTypeOperationAndDate(typeOperation, formattedDate);

            for (CRA cra : cras) {
                List<CRAOperation> craOperations = craOperationRepository.findByCra(cra);
                for (CRAOperation craOperation : craOperations) {
                    CRAOperationResponse craOperationResponse = new CRAOperationResponse(
                            craOperation.getId(),
                            craOperation.getOperation(),
                            craOperation.getRejet(),
                            craOperation.getCra(),
                            craOperation.getCodeRejetOperation(),
                            craOperation.getRIO(),
                            craOperation.getMontant()

                    );

                    craOperationResponses.add(craOperationResponse);
                }
            }
            if (!craOperationResponses.isEmpty()) {
                return ResponseEntity.ok(craOperationResponses);
            } else {
                throw new RuntimeException("Erreur: Operations non trouvées pour le type " + typeOperation + " et la date " + formattedDate);
            }
        } else if (Arrays.asList(rejetTypes).contains(typeOperation)) {
            String formattedDate = formatDate(date);
            List<CRAOperationResponse> craOperationResponses = new ArrayList<>();
            List<CRA> cras = craRepository.findByTypeOperationAndDate(typeOperation, formattedDate);

            for (CRA cra : cras) {
                List<CRAOperation> craOperations = craOperationRepository.findByCra(cra);
                for (CRAOperation craOperation : craOperations) {

                    CRAOperationResponse craOperationResponse = new CRAOperationResponse(
                            craOperation.getId(),
                            craOperation.getOperation(),
                            craOperation.getRejet(),
                            craOperation.getCra(),
                            craOperation.getCodeRejetOperation(),
                            craOperation.getRIO(),
                            craOperation.getMontant()

                    );

                    craOperationResponses.add(craOperationResponse);
                }
            }
            if (!craOperationResponses.isEmpty()) {
                return ResponseEntity.ok(craOperationResponses);
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
