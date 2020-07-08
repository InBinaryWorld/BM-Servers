package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    private Long id;
    @NotNull
    private String country;
    @NotNull
    private String postalCode;
    @NotNull
    private String city;
    @NotNull
    private String addressLine1;
    private String addressLine2;
}
