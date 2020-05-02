package dev.szafraniak.bmresourceserver.rest.v1.entity;

import lombok.Data;

@Data
public class Address {
    private String id;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
}
