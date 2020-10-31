package dev.szafraniak.bmresource.model.action.invoice;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceOrderItemModel {

    private String name;
    private String quantityUnit;
    private BigDecimal quantity;
    private BigDecimal taxRate;
    private BigDecimal priceNet;
    private BigDecimal net;
    private BigDecimal tax;
    private BigDecimal gross;
}
