package com.example.batch.repository;

import java.util.Optional;

import com.example.batch.models.ERole;
import com.example.batch.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
