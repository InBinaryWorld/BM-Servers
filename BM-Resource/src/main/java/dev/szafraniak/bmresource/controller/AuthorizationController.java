package dev.szafraniak.bmresource.controller;

import dev.szafraniak.bmresource.utils.AuthData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/token")
public class AuthorizationController {

    @Value("${keycloak.auth-server-url}")
    private String keycloakPath;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    public static final String PROVIDER_GOOGLE = "google";
    public static final String PROVIDER_FACEBOOK = "facebook";
    private static final String keycloakAuthClient = "bm-mobile";

    @PostMapping(value = "/google/exchange", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> exchangeGoogle(AuthData data) {
        return socialExchange(data.getToken(), PROVIDER_GOOGLE);
    }

    @PostMapping(value = "/facebook/exchange", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> exchangeFacebook(AuthData data) {
        return socialExchange(data.getToken(), PROVIDER_FACEBOOK);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> credentialsLogin(AuthData data) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("client_id", keycloakAuthClient);
        map.add("username", data.username);
        map.add("password", data.password);
        return postFromUrlEncoded(map);
    }

    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> refreshToken(AuthData data) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", data.refreshToken);
        map.add("client_id", keycloakAuthClient);
        return postFromUrlEncoded(map);
    }

    private ResponseEntity<String> socialExchange(String token, String provider) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "urn:ietf:params:oauth:grant-type:token-exchange");
        map.add("subject_token_type", "urn:ietf:params:oauth:token-type:access_token");
        map.add("client_id", keycloakAuthClient);
        map.add("subject_token", token);
        map.add("subject_issuer", provider);
        return postFromUrlEncoded(map);
    }

    private ResponseEntity<String> postFromUrlEncoded(MultiValueMap<String, String> map) {
        String KEYCLOAK_URL = keycloakPath + "/realms/" + keycloakRealm + "/protocol/openid-connect/token";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new IgnoreHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity(KEYCLOAK_URL, request, String.class);
    }

    private static class IgnoreHandler implements ResponseErrorHandler {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) {
        }
    }

}