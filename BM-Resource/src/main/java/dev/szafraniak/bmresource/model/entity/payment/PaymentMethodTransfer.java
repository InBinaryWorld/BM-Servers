package dev.szafraniak.bmresource.model.entity.payment;

import lombok.Data;

@Data
public class PaymentMethodTransfer implements PaymentMethod {

    private String name;

    private String bankAccount;
}
