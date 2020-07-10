package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Company company;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Address address;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "warehouse")
    private List<Product> products;

}
