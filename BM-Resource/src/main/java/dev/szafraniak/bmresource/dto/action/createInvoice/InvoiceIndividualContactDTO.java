package dev.szafraniak.bmresource.dto.action.createInvoice;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvoiceIndividualContactDTO extends InvoiceContactDTO {

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String firstName;

    @NotNull
    @Pattern(regexp = Regexps.WORD_1_20)
    private String lastName;

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getTaxIdentityNumber() {
        return null;
    }
}
