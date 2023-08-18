package com.example.batch.repository;

import com.example.batch.models.CRA;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CRARepository extends MongoRepository<CRA, String> {
    Optional<CRA> findById(String Id);

    List<CRA> findByTypeOperationAndDate(String typeOperation, String dateStr);
}