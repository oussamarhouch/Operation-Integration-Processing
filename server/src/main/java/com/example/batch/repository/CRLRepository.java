package com.example.batch.repository;

import com.example.batch.models.CRL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CRLRepository extends MongoRepository<CRL, String> {
    Optional<CRL> findById(String Id);

    List<CRL> findByTypeOperationAndDate(String typeOperation, String date);
}