package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.entity.base.BaseCompanyEntity;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
public class Warehouse extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @Valid
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "warehouse")
    private List<Product> products;

    @Valid
    @NotNull
    @ManyToOne
    private Company company;

}
