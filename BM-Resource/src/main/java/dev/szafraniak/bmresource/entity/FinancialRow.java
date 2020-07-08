package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class FinancialRow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_row_gen")
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime eventDate;

    private String description;

    private BigDecimal amountChange;

}
