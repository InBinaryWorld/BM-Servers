package dev.szafraniak.bmresource.dto.action.createInvoice.payment;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentMethodTransferDTO extends PaymentMethodDTO {

    @NotNull
    @Pattern(regexp = Regexps.BANK_ACCOUNT)
    private String bankAccount;
}
