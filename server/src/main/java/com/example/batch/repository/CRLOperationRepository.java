package com.example.batch.repository;

import com.example.batch.models.CRL;
import com.example.batch.models.CRLOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CRLOperationRepository extends MongoRepository<CRLOperation, String> {
    List<CRLOperation> findByCrl(CRL crl);
}
