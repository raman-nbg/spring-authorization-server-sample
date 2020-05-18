package com.github.ramannbg.springauthorizationserversample.authorizationserver.config;

import com.github.ramannbg.springauthorizationserversample.authorizationserver.service.ClientDetailsServiceFactory;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

import static java.util.Collections.singletonMap;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final ClientDetailsServiceFactory clientDetailsServiceFactory;

    @Autowired
    public AuthorizationServerConfig(ClientDetailsServiceFactory clientDetailsServiceFactory) {
        this.clientDetailsServiceFactory = clientDetailsServiceFactory;
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsServiceFactory.createClientDetailService());
//        clients.inMemory()
//            .withClient("first-client")
//            .secret(passwordEncoder().encode("thesecret"))
//            .scopes("project:read", "project:create")
//            .authorizedGrantTypes("authorization_code")
//            .redirectUris("http://localhost:3000/")
//            .autoApprove("project:read", "project:create");
    }

    @Bean
    public KeyPair keyPair() {
        ClassPathResource ksFile = new ClassPathResource("bael-jwt.jks");
        KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, "bael-pass".toCharArray());

        return ksFactory.getKeyPair("bael-oauth-jwt");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(KeyPair keyPair) {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter(
                singletonMap("kid", "bael-key-id"),
                keyPair
        );

        return converter;
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
