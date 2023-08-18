package com.example.batch.repository;

import com.example.batch.models.LotMere;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotMereRepository extends MongoRepository<LotMere, String> {
    boolean existsByEnteteAndDate(String entete, String date);

    Optional<LotMere> findByLotMereId(ObjectId lotMereId);

    LotMere findByEnteteAndDate(String entete, String date);
}
