package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String quantityUnit;

    private String bareCode;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Price priceSuggestion;

    @ManyToOne
    private ProductGroup productGroup;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "productModel")
    private List<Product> products;

    @NotNull
    @ManyToOne
    private Company company;
}
