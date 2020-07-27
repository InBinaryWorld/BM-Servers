package dev.szafraniak.bmresource.controller;

import dev.szafraniak.bmresource.config.BaseEnvironment;
import dev.szafraniak.bmresource.services.AuthorizationService;
import dev.szafraniak.bmresource.utils.AuthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class AuthorizationController {

    private AuthorizationService authService;

    @PostMapping(value = "/google/exchange", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> exchangeGoogle(AuthData data) {
        return socialExchange(data.getToken(), BaseEnvironment.AUTH_PROVIDER_GOOGLE);
    }

    @PostMapping(value = "/facebook/exchange", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> exchangeFacebook(AuthData data) {
        return socialExchange(data.getToken(), BaseEnvironment.AUTH_PROVIDER_FACEBOOK);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> credentialsLogin(AuthData data) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", data.username);
        map.add("password", data.password);
        return authService.postFromUrlEncoded(map);
    }

    @PostMapping(value = "/refresh", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> refreshToken(AuthData data) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", data.refreshToken);
        return authService.postFromUrlEncoded(map);
    }

    private ResponseEntity<String> socialExchange(String token, String provider) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "urn:ietf:params:oauth:grant-type:token-exchange");
        map.add("subject_token_type", "urn:ietf:params:oauth:token-type:access_token");
        map.add("subject_token", token);
        map.add("subject_issuer", provider);
        return authService.postFromUrlEncoded(map);
    }

    @Autowired
    public void setAuthService(AuthorizationService authService) {
        this.authService = authService;
    }
}