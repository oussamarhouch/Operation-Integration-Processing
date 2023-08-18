package com.example.batch.repository;

import com.example.batch.models.CRO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CRORepository extends MongoRepository<CRO, String> {
    Optional<CRO> findById(String Id);

    List<CRO> findByTypeOperationAndDate(String typeOperation, String dateStr);
}
