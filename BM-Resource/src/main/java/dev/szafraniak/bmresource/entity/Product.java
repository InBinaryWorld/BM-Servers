package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.entity.base.BaseCompanyEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
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
