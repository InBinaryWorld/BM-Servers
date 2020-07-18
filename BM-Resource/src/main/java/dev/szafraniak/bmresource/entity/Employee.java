package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE)
    private IndividualContact individualContact;

    @NotNull
    private String employmentState;

    private String jobPosition;

    private LocalDate firstEmploymentDate;

    private BigDecimal salary;

    @NotNull
    @ManyToOne
    private Company company;

}
