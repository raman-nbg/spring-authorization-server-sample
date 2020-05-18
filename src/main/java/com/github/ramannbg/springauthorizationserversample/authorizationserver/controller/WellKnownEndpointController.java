package com.github.ramannbg.springauthorizationserversample.authorizationserver.controller;

import com.github.ramannbg.springauthorizationserversample.authorizationserver.model.OpenIdConfiguration;
import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/.well-known/")
public class WellKnownEndpointController {
    private final JWKSet jwkSet;

    @Autowired
    public WellKnownEndpointController(JWKSet jwkSet) {
        this.jwkSet = jwkSet;
    }

    @GetMapping("jwks.json")
    public Map<String, Object> getJsonWebKeySet() {
        return this.jwkSet.toJSONObject();
    }

    @GetMapping("openid_configuration")
    public OpenIdConfiguration getOpenIdConfiguration() {
        OpenIdConfiguration config = new OpenIdConfiguration();

        // TODO: Set config properties

        return config;
    }
}
