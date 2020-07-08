package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class BusinessContact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_contact_gen")
    private Long id;

    @NotNull
    private String type;

    @NotNull
    private String name;

    private String taxIdentityNumber;

    @NotNull
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Address address;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ContactMedium> contactMedia;

}
