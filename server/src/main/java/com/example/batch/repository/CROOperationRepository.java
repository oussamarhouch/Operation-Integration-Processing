package com.example.batch.repository;

import com.example.batch.models.CRO;
import com.example.batch.models.CROOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CROOperationRepository extends MongoRepository<CROOperation, String> {
    List<CROOperation> findByCro(CRO cro);
}