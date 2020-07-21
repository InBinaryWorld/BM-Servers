package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.entity.base.BaseCompanyEntity;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class BankAccount extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @NotNull
    @Pattern(regexp = Regexps.BANK_ACCOUNT)
    private String value;

    @NotNull
    @ManyToOne
    private Company company;
}
