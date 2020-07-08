package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
    private Long id;

    @NotNull
    private BigDecimal quantity;

    @NotNull
    @ManyToOne
    @JsonManagedReference
    private ProductModel productModel;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private Warehouse warehouse;

}
