package dev.szafraniak.bmresourceserver.rest.v1.entity.client;

import dev.szafraniak.bmresourceserver.rest.v1.entity.Address;
import dev.szafraniak.bmresourceserver.rest.v1.entity.person.Contact;
import lombok.Data;

import java.util.List;

@Data
public class ClientCompany {
    private String id;
    private String name;
    private String nip;
    private Address address;
    private List<Contact> contacts;
}
