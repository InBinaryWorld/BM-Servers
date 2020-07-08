package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_gen")
    private Long id;

    @NotNull
    @OneToOne
    @JsonManagedReference
    private BusinessContact businessContact;

    @NotNull
    private String employmentState;

    private LocalDate firstEmploymentDate;

    private String jobPosition;

    private BigDecimal salary;

}
