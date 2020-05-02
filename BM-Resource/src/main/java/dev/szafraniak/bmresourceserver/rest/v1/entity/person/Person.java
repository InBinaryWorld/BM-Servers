package dev.szafraniak.bmresourceserver.rest.v1.entity.person;

import dev.szafraniak.bmresourceserver.rest.v1.entity.Address;
import lombok.Data;

import java.util.List;

@Data
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private Address address;
    private List<Contact> contacts;
}
