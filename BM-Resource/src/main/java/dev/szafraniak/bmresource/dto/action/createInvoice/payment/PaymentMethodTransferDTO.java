package dev.szafraniak.bmresource.dto.action.createInvoice.payment;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentMethodTransferDTO extends PaymentMethodDTO {

    @Pattern(regexp = Regexps.BASE_2_40)
    private String accountName;

    @NotNull
    @Pattern(regexp = Regexps.BANK_ACCOUNT)
    private String accountNumber;
}
