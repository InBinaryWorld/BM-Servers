package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class BusinessContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;

    @NotNull
    private String name;

    private String taxIdentityNumber;

    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Address address;

    @OneToMany(mappedBy = "businessContact")
    private List<Invoice> invoice;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "businessContact")
    private List<ContactMedium> contactMedia;

    @NotNull
    @ManyToOne
    private Company company;

}
