package com.github.ramannbg.springauthorizationserversample.authorizationserver.service;

import com.github.ramannbg.springauthorizationserversample.authorizationserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * Provides client details from a custom data source. The
 * data source is accessed via {@link ClientRepository}.
 */
@Service
@Primary
public class ClientDetailsServiceImpl implements ClientDetailsService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientRepository.findByClientId(clientId).orElseThrow(() -> {
            throw new ClientRegistrationException("Could not find client with id " + clientId);
        });
    }
}
