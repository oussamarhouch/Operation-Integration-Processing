package com.example.batch.repository;

import com.example.batch.models.LotRejet;
import com.example.batch.models.Rejet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RejetRepository extends MongoRepository<Rejet, String> {
    Rejet findByLotRejetAndReferenceOperation(LotRejet lotRejet, String referenceOperation);

    List<Rejet> findByLotRejet(LotRejet lotRejet);
}