package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.validator.EnvironmentIds;
import dev.szafraniak.bmresource.validator.VerifyEnvironmentId;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @VerifyEnvironmentId(source = EnvironmentIds.QUANTITY_UNIT_SERVICE)
    private String quantityUnit;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Price priceSuggestion;

    @NotNull
    @ManyToOne
    private Company company;

}
