package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ContactMedium {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_medium_gen")
    private Long id;

    @NotNull
    private String contactType;

    @NotNull
    private String value;

}
