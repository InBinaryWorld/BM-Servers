package dev.szafraniak.bmresource.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    @NotNull
    private BigDecimal quantity;

    @Valid
    @NotNull
    @ManyToOne
    private ProductModel productModel;

    @Valid
    @NotNull
    @ManyToOne
    private Warehouse warehouse;

    @Valid
    @NotNull
    @ManyToOne
    private Company company;

}
