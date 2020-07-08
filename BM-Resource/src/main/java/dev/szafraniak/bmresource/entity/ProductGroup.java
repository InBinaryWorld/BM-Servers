package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_group_gen")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToMany
    @JsonBackReference
    private List<ProductModel> productModels;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private User owner;

}
