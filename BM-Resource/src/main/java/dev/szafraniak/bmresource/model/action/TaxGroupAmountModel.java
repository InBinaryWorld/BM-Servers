package dev.szafraniak.bmresource.model.action;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxGroupAmountModel {
    public TaxGroupAmountModel(AmountModel amount, BigDecimal taxRate) {
        this.net = amount.getNet();
        this.tax = amount.getTax();
        this.gross = amount.getGross();
        this.taxRate = taxRate;
    }

    private BigDecimal net;
    private BigDecimal tax;
    private BigDecimal gross;
    private BigDecimal taxRate;
}
