package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.entity.base.BaseCompanyEntity;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class FinancialRow extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_60)
    private String title;

    @NotNull
    private LocalDateTime eventDate;

    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_240)
    private String description;

    @NotNull
    private BigDecimal amountChange;

    @NotNull
    @ManyToOne
    private Company company;

}
