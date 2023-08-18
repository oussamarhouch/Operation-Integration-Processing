package com.example.batch.controllers;

import com.example.batch.models.LotOperation;
import com.example.batch.models.LotRejet;
import com.example.batch.models.Operation;
import com.example.batch.models.Rejet;
import com.example.batch.payload.response.LotOperationResponse;
import com.example.batch.payload.response.LotRejetResponse;
import com.example.batch.payload.response.OperationResponse;
import com.example.batch.payload.response.RejetResponse;
import com.example.batch.repository.LotOperationRepository;
import com.example.batch.repository.LotRejetRepository;
import com.example.batch.repository.OperationRepository;
import com.example.batch.repository.RejetRepository;
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
@RequestMapping("/api/lot")
public class LOTController {

    @Autowired
    LotOperationRepository lotOperationRepository;

    @Autowired
    LotRejetRepository lotRejetRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    RejetRepository rejetRepository;

    String[] operationTypes = {"010", "012", "013", "020", "031", "060"};
    String[] rejetTypes = {"120", "131", "160"};

    @GetMapping("/{id}")
    public ResponseEntity<?> getLotbyId(@PathVariable String id) {
        Optional<LotOperation> lotOperation = lotOperationRepository.findById(id);
        Optional<LotRejet> lotRejet = lotRejetRepository.findById(id);
        if (lotOperation.isPresent()) {
            return ResponseEntity.ok(new LotOperationResponse(lotOperation.get().getId(), lotOperation.get().getDate(), lotOperation.get().getTime(),
                    lotOperation.get().getLotMere().getLotMereId(), lotOperation.get().getTypeOperation(), lotOperation.get().getIdentifiantBanque(),
                    lotOperation.get().getIdentifiantSource(), lotOperation.get().getNumeroLot(), lotOperation.get().getDevise(), lotOperation.get().getNombreOperation(),
                    lotOperation.get().getMontant()));

        } else if (lotRejet.isPresent()) {
            return ResponseEntity.ok(new LotRejetResponse(lotRejet.get().getId(), lotRejet.get().getDate(), lotRejet.get().getTime(),
                    lotRejet.get().getLotMere().getLotMereId(), lotRejet.get().getTypeOperation(), lotRejet.get().getIdentifiantBanque(),
                    lotRejet.get().getIdentifiantSource(), lotRejet.get().getNumeroLot(), lotRejet.get().getDevise(), lotRejet.get().getNombreRejet()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{typeOperation}/{date}")
    public ResponseEntity<?> getLot(@PathVariable String typeOperation, @PathVariable String date) {
        String dateStr = date.substring(4, 8) + "-" + date.substring(2, 4) + "-" + date.substring(0, 2);
        if (Arrays.asList(operationTypes).contains(typeOperation)) {
            List<LotOperationResponse> lotOperationResponses = new ArrayList<>();
            List<LotOperation> lotOperations = lotOperationRepository.findByTypeOperationAndDate(typeOperation, dateStr);

            for (LotOperation lotOperation : lotOperations) {
                LotOperationResponse lotOperationResponse = new LotOperationResponse(lotOperation.getId(), lotOperation.getDate(), lotOperation.getTime(),
                        lotOperation.getLotMere().getLotMereId(), lotOperation.getTypeOperation(), lotOperation.getIdentifiantBanque(),
                        lotOperation.getIdentifiantSource(), lotOperation.getNumeroLot(), lotOperation.getDevise(), lotOperation.getNombreOperation(),
                        lotOperation.getMontant());

                lotOperationResponses.add(lotOperationResponse);
            }
            if (!lotOperationResponses.isEmpty()) {
                return ResponseEntity.ok(lotOperationResponses);
            } else {
                throw new RuntimeException("Erreur: Lots Operations non trouvés pour le type " + typeOperation + " et la date " + dateStr);
            }
        } else if (Arrays.asList(rejetTypes).contains(typeOperation)) {

            List<LotRejetResponse> lotRejetResponses = new ArrayList<>();
            List<LotRejet> lotrejets = lotRejetRepository.findByTypeOperationAndDate(typeOperation, dateStr);

            for (LotRejet lotRejet : lotrejets) {
                LotRejetResponse lotRejetResponse = new LotRejetResponse(lotRejet.getId(), lotRejet.getDate(), lotRejet.getTime(),
                        lotRejet.getLotMere().getLotMereId(), lotRejet.getTypeOperation(), lotRejet.getIdentifiantBanque(),
                        lotRejet.getIdentifiantSource(), lotRejet.getNumeroLot(), lotRejet.getDevise(), lotRejet.getNombreRejet());
                lotRejetResponses.add(lotRejetResponse);
            }
            if (!lotRejetResponses.isEmpty()) {
                return ResponseEntity.ok(lotRejetResponses);
            } else {
                throw new RuntimeException("Erreur: Lots Rejets non trouvés pour le type " + typeOperation + " et la date " + dateStr);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("operation/{typeOperation}/{date}")
    public ResponseEntity<List<OperationResponse>> getOperations(@PathVariable String typeOperation, @PathVariable String date) {
        String formattedDate = formatDate(date);

        if (!Arrays.asList(operationTypes).contains(typeOperation)) {
            return ResponseEntity.notFound().build();
        }

        List<OperationResponse> operationResponses = new ArrayList<>();
        List<LotOperation> lotOperations = lotOperationRepository.findByTypeOperationAndDate(typeOperation, formattedDate);

        lotOperations.forEach(lotOperation -> {
            List<Operation> operations = operationRepository.findByLotOperation(lotOperation);
            operations.forEach(operation -> {
                OperationResponse operationResponse = new OperationResponse(
                        operation.getId(),
                        operation.getLotOperation().getId(),
                        operation.getReferenceOperation(),
                        operation.getDonneur(),
                        operation.getBeneficiaire(),
                        operation.getMontant()
                );
                operationResponses.add(operationResponse);
            });
        });

        if (!operationResponses.isEmpty()) {
            return ResponseEntity.ok(operationResponses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("rejet/{typeOperation}/{date}")
    public ResponseEntity<?> getRejets(@PathVariable String typeOperation, @PathVariable String date) {
        String formattedDate = formatDate(date);

        if (!Arrays.asList(rejetTypes).contains(typeOperation)) {
            return ResponseEntity.notFound().build();
        }

        List<RejetResponse> rejetResponses = new ArrayList<>();
        List<LotRejet> lotRejets = lotRejetRepository.findByTypeOperationAndDate(typeOperation, formattedDate);
        for (LotRejet lotRejet : lotRejets) {
            List<Rejet> rejets = rejetRepository.findByLotRejet(lotRejet);
            for (Rejet rejet : rejets) {

                RejetResponse rejetResponse = new RejetResponse(
                        rejet.getId(),
                        rejet.getLotRejet().getId(),
                        rejet.getReferenceOperation(),
                        rejet.getMotifRejet(),
                        rejet.getRIO()
                );

                rejetResponses.add(rejetResponse);
            }
        }

        if (!rejetResponses.isEmpty()) {
            return ResponseEntity.ok(rejetResponses);
        } else {
            throw new RuntimeException("Erreur: Operations non trouvées pour le type " + typeOperation + " et la date " + formattedDate);
        }
    }

    private String formatDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, inputFormatter);
        return outputFormatter.format(parsedDate);
    }
}
