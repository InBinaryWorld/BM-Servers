package dev.szafraniak.bmresource.model.entity;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class ProductModel extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_1_6)
    private String quantityUnit;

    @Pattern(regexp = Regexps.BARCODE_5_20)
    private String barcode;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Price priceSuggestion;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "productModel")
    private List<Product> products;

    @NotNull
    @ManyToOne
    private Company company;
}
