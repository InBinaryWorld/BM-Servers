package dev.szafraniak.bmresource.model.entity.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentMethodTransfer extends PaymentMethod {

    private String bankAccount;
}
