package com.example.batch.repository;

import com.example.batch.models.CRA;
import com.example.batch.models.CRAOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CRAOperationRepository extends MongoRepository<CRAOperation, String> {

    List<CRAOperation> findByCra(CRA cra);
}
