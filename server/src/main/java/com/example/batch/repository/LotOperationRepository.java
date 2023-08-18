package com.example.batch.repository;

import com.example.batch.models.LotMere;
import com.example.batch.models.LotOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotOperationRepository extends MongoRepository<LotOperation, String> {

    Optional<LotOperation> findById(String Id);

    List<LotOperation> findByTypeOperation(String typeOperation);

    List<LotOperation> findByTypeOperationAndDate(String typeOperation, String date);

    boolean existsOperationByIdentifiantBanqueAndIdentifiantSourceAndLotMereAndNumeroLotAndDeviseAndTypeOperationAndDateAndMontant(
            String identifiantBanque, String identifiantSource, LotMere LotMere, String numeroLot, String devise, String typeOperation, long montant, String date);

    LotOperation findLotOperationByDateAndIdentifiantBanqueAndIdentifiantSourceAndNumeroLotAndTypeOperationAndDevise(String formattedDate, String identifiantBanque, String identifiantSource, String numeroLot, String typeOperation, String devise);
}