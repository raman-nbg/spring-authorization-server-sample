package com.github.ramannbg.springauthorizationserversample.authorizationserver.controller;

import com.github.ramannbg.springauthorizationserversample.authorizationserver.model.OpenIdConfiguration;
import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/.well-known/")
@CrossOrigin("*")
public class WellKnownEndpointController {
    private final JWKSet jwkSet;

    @Value("${authorization-server.public-base-url}")
    private String publicBaseUrl;

    @Autowired
    public WellKnownEndpointController(JWKSet jwkSet) {
        this.jwkSet = jwkSet;
    }

    @GetMapping("jwks.json")
    public Map<String, Object> getJsonWebKeySet() {
        return this.jwkSet.toJSONObject();
    }

    @GetMapping("openid-configuration")
    public OpenIdConfiguration getOpenIdConfiguration() {
        OpenIdConfiguration config = new OpenIdConfiguration();

        config.setAuthorizationEndpoint(createUrl("/oauth/authorize"));
        config.setTokenEndpoint(createUrl("/oauth/token"));
        config.setUserInfoEndpoint(createUrl("/oauth/userinfo"));
        config.setJwksUri(createUrl("/.well-known/jwks.json"));
        config.setIssuer(publicBaseUrl);
        config.setIdTokenSigningAlgValuesSupported(asSet("RS512"));
        config.setScopesSupported(asSet("openid", "profile", "email"));
        config.setResponseModesSupported(asSet("form_post", "fragment", "query"));
        config.setResponseTypesSupported(asSet("code", "id_token", "token id_token"));
        config.setSubjectTypesSupported(asSet("public"));

        return config;
    }

    private Set<String> asSet(String... values) {
        return new HashSet<>(asList(values));
    }

    private String createUrl(String path) {
        return publicBaseUrl + path;
    }
}
