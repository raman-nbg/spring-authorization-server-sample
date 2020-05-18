package com.github.ramannbg.springauthorizationserversample.authorizationserver.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import static java.util.Collections.singletonMap;

@Configuration
public class JwkSetConfiguration {
    @Bean
    public KeyPair keyPair() {
        ClassPathResource ksFile = new ClassPathResource("bael-jwt.jks");
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, "bael-pass".toCharArray());

        return ksFactory.getKeyPair("bael-oauth-jwt");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(KeyPair keyPair) {
        return new CustomJwtAccessTokenConverter(
                singletonMap("kid", "bael-key-id"),
                keyPair
        );
    }

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter converter) {
        return new JwtTokenStore(converter);
    }

    @Bean
    public JWKSet jwkSet(KeyPair keyPair) {
        RSAKey.Builder builder =
                new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                        .keyUse(KeyUse.SIGNATURE)
                        .algorithm(JWSAlgorithm.RS256)
                        .keyID("bael-key-id");

        return new JWKSet(builder.build());
    }
}
