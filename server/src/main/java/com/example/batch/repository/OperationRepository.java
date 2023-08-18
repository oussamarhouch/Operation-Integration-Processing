package com.example.batch.repository;

import com.example.batch.models.LotOperation;
import com.example.batch.models.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends MongoRepository<Operation, String> {

    Operation findByLotOperationAndReferenceOperation(LotOperation lotOperation, String referenceOperation);

    List<Operation> findByLotOperation(LotOperation lotOperation);
}
