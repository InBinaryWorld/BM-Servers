package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ContactMedium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String contactType;

    @NotNull
    private String value;

    @NotNull
    @ManyToOne
    private BusinessContact businessContact;

}
