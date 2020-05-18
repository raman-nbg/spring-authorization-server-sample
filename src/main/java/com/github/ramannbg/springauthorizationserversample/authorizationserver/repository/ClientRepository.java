package com.github.ramannbg.springauthorizationserversample.authorizationserver.repository;

import com.github.ramannbg.springauthorizationserversample.authorizationserver.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}
