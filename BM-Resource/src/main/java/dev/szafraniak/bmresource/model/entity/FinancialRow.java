package dev.szafraniak.bmresource.model.entity;

import dev.szafraniak.bmresource.annotations.nullOrNotBlank.NullOrNotBlank;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class FinancialRow extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 60)
    @Pattern(regexp = Regexps.BASE_2_60)
    private String title;

    @NotNull
    private OffsetDateTime eventDate;

    @NullOrNotBlank
    @Length(max = 240)
    @Pattern(regexp = Regexps.BASE_2_240)
    private String description;

    @NotNull
    private BigDecimal amountChange;

    @NotNull
    @ManyToOne
    private Company company;

}
