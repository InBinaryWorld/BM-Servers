package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String country;

    @NotNull
    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String postalCode;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String city;

    @NotNull
    @Pattern(regexp = "[^\\t\\n\\r\\f\\x0B]{1,45}")
    private String addressLine1;

    @Pattern(regexp = "[^\\t\\n\\r\\f\\x0B]{1,45}")
    private String addressLine2;
}
