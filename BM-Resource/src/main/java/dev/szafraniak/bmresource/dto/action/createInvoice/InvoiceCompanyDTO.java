package dev.szafraniak.bmresource.dto.action.createInvoice;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvoiceCompanyDTO extends InvoiceContactDTO {


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

    @Override
    public String getTaxIdentityNumber() {
        return taxIdentityNumber;
    }
}
