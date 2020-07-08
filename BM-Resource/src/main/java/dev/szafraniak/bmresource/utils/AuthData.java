package dev.szafraniak.bmresource.utils;

import lombok.Data;

@Data
public class AuthData {
    public String token;
    public String refreshToken;
    public String username;
    public String password;
}
