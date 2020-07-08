package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class ServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_model_gen")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String quantityUnit;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Price priceSuggestion;
    
}
