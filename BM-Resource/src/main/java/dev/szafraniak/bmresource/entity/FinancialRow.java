package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class FinancialRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime eventDate;

    private String description;

    private BigDecimal amountChange;

    @NotNull
    @ManyToOne
    private Company company;

}
