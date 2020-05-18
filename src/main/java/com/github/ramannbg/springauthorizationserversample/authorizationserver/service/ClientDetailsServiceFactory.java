package com.github.ramannbg.springauthorizationserversample.authorizationserver.service;

import com.github.ramannbg.springauthorizationserversample.authorizationserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * This factory creates a client detail service that provides
 * a custom client, that is stored in a own database. The
 * database is accessed via {@link ClientRepository}.
 */
@Service
public class ClientDetailsServiceFactory {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailsServiceFactory(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDetailsService createClientDetailService() {
        return clientId -> clientRepository.findByClientId(clientId).orElseThrow(() -> {
            throw new ClientRegistrationException("Could not find client with id " + clientId);
        });
    }
}
