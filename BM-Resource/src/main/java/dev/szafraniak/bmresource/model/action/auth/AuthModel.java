package dev.szafraniak.bmresource.model.action.auth;

import lombok.Data;

@Data
public class AuthModel {
    public String token;
    public String refreshToken;
    public String username;
    public String password;
}
