package dev.szafraniak.bmresource.model.entity.contact;

import dev.szafraniak.bmresource.model.entity.Address;
import dev.szafraniak.bmresource.model.entity.BaseCompanyEntity;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.Invoice;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
public abstract class Contact extends BaseCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 12)
    @Pattern(regexp = Regexps.PHONE_4_12)
    private String phone;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @NotNull
    @ManyToOne
    private Company company;

    public abstract String getName();

}
