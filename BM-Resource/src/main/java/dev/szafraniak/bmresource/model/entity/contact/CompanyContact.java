package dev.szafraniak.bmresource.model.entity.contact;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CompanyContact extends Contact {

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @NotNull
    @Pattern(regexp = Regexps.TAX_IDENTITY_NUMBER)
    private String taxIdentityNumber;

    @Override
    public String getName() {
        return name;
    }

}
