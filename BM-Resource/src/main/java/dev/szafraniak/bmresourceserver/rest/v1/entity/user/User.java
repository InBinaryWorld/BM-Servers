package dev.szafraniak.bmresourceserver.rest.v1.entity.user;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String id;
    private String email;
    private String username;
    private String language;
    private List<Company> companies;
}
