package dev.szafraniak.bmresourceserver.rest.v1.entity.person;

import lombok.Data;

@Data
public class Contact {
    private String id;
    private String type;
    private String value;
}
