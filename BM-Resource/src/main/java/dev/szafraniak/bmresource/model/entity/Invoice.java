package dev.szafraniak.bmresource.model.entity;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Invoice extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 40)
    @Pattern(regexp = Regexps.BASE_2_40)
    private String buyerName;

    @NotNull
    @NotBlank
    @Length(max = 100)
    private String fileReference;

    @NotNull
    private Boolean isPaid;

    @NotNull
    @NotBlank
    @Length(max = 20)
    private String invoiceName;

    @NotNull
    private OffsetDateTime creationDate;

    private OffsetDateTime dateOfPayment;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private LocalDate sellDate;

    @NotNull
    private Boolean splitPayment;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Amount totalAmount;

    @Valid
    @NotNull
    @ManyToOne
    private Company company;

}
