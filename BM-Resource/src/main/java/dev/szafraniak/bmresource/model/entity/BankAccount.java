package dev.szafraniak.bmresource.model.entity;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class BankAccount extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 40)
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @NotNull
    @Length(max = 40)
    @Pattern(regexp = Regexps.BANK_ACCOUNT)
    private String value;

    @NotNull
    @ManyToOne
    private Company company;
}
