package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.entity.base.BaseCompanyEntity;
import dev.szafraniak.bmresource.utils.Regexps;
import dev.szafraniak.bmresource.validator.EnvironmentIds;
import dev.szafraniak.bmresource.validator.VerifyEnvironmentId;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
public class ProductModel extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_60)
    private String name;

    @NotNull
    @VerifyEnvironmentId(source = EnvironmentIds.QUANTITY_UNIT_PRODUCT)
    private String quantityUnitId;


    @Pattern(regexp = Regexps.BARCODE_5_20)
    private String bareCode;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Price priceSuggestion;

    @ManyToOne
    private ProductGroup productGroup;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "productModel")
    private List<Product> products;

    @NotNull
    @ManyToOne
    private Company company;
}
