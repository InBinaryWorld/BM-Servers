package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_model_gen")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String quantityUnit;

    private String bareCode;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Price priceSuggestion;

    @ManyToMany
    @JsonManagedReference
    private List<ProductGroup> productGroups;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Product> products;
}
