package com.example.batch.repository;

import com.example.batch.models.LotMere;
import com.example.batch.models.LotRejet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotRejetRepository extends MongoRepository<LotRejet, String> {
    List<LotRejet> findByTypeOperation(String typeOperation);

    Optional<LotRejet> findById(String Id);

    List<LotRejet> findByTypeOperationAndDate(String typeOperation, String date);

    LotRejet findByIdentifiantBanqueAndIdentifiantSourceAndNumeroLotAndTypeOperationAndDeviseAndDate(String identifiantBanque, String identifiantSource, String numeroLot, String typeOperation, String devise, String date);


    boolean existsRejetByIdentifiantBanqueAndIdentifiantSourceAndLotMereAndNumeroLotAndDeviseAndTypeOperationAndDate(
            String identifiantBanque, String identifiantSource, LotMere LotMere, String numeroLot, String devise, String typeOperation, String date);
}