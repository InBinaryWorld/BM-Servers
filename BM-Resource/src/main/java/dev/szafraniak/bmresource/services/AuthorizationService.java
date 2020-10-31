package dev.szafraniak.bmresource.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthorizationService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakPath;

    @Value("${keycloak.realm}")
    private String keycloakRealm;


    private static final String keycloakAuthClient = "bm-mobile";

    public ResponseEntity<String> postFromUrlEncoded(MultiValueMap<String, String> map) {
        String KEYCLOAK_URL = String.format("%s%s%s%s", keycloakPath, "/realms/", keycloakRealm, "/protocol/openid-connect/token");
        HttpEntity<MultiValueMap<String, String>> request = createRequest(map);
        RestTemplate restTemplate = createTemplate();
        return restTemplate.postForEntity(KEYCLOAK_URL, request, String.class);
    }

    private RestTemplate createTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new IgnoreHandler());
        return restTemplate;
    }

    private HttpEntity<MultiValueMap<String, String>> createRequest(MultiValueMap<String, String> map) {
        map.add("client_id", keycloakAuthClient);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(map, headers);
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
