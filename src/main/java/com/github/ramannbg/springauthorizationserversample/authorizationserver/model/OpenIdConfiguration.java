package com.github.ramannbg.springauthorizationserversample.authorizationserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class OpenIdConfiguration {
    private String issuer;
    @JsonProperty("authorization_endpoint")
    private String authorizationEndpoint;
    @JsonProperty("token_endpoint")
    private String tokenEndpoint;
    @JsonProperty("userinfo_endpoint")
    private String userInfoEndpoint;
    @JsonProperty("jwks_uri")
    private String jwksUri;
    @JsonProperty("scopes_supported")
    private Set<String> scopesSupported;
    @JsonProperty("response_types_supported")
    private Set<String> responseTypesSupported;
    @JsonProperty("response_modes_supported")
    private Set<String> responseModesSupported;
    @JsonProperty("subject_types_supported")
    private Set<String> subjectTypesSupported;
    @JsonProperty("id_token_signing_alg_values_supported")
    private Set<String> idTokenSigningAlgValuesSupported;
    @JsonProperty("token_endpoint_auth_methods_supported")
    private Set<String> tokenEndpointAuthMethodsSupported;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    public void setAuthorizationEndpoint(String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getUserInfoEndpoint() {
        return userInfoEndpoint;
    }

    public void setUserInfoEndpoint(String userInfoEndpoint) {
        this.userInfoEndpoint = userInfoEndpoint;
    }

    public String getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(String jwksUri) {
        this.jwksUri = jwksUri;
    }

    public Set<String> getScopesSupported() {
        return scopesSupported;
    }

    public void setScopesSupported(Set<String> scopesSupported) {
        this.scopesSupported = scopesSupported;
    }

    public Set<String> getResponseTypesSupported() {
        return responseTypesSupported;
    }

    public void setResponseTypesSupported(Set<String> responseTypesSupported) {
        this.responseTypesSupported = responseTypesSupported;
    }

    public Set<String> getResponseModesSupported() {
        return responseModesSupported;
    }

    public void setResponseModesSupported(Set<String> responseModesSupported) {
        this.responseModesSupported = responseModesSupported;
    }

    public Set<String> getSubjectTypesSupported() {
        return subjectTypesSupported;
    }

    public void setSubjectTypesSupported(Set<String> subjectTypesSupported) {
        this.subjectTypesSupported = subjectTypesSupported;
    }

    public Set<String> getIdTokenSigningAlgValuesSupported() {
        return idTokenSigningAlgValuesSupported;
    }

    public void setIdTokenSigningAlgValuesSupported(Set<String> idTokenSigningAlgValuesSupported) {
        this.idTokenSigningAlgValuesSupported = idTokenSigningAlgValuesSupported;
    }

    public Set<String> getTokenEndpointAuthMethodsSupported() {
        return tokenEndpointAuthMethodsSupported;
    }

    public void setTokenEndpointAuthMethodsSupported(Set<String> tokenEndpointAuthMethodsSupported) {
        this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
    }
}
