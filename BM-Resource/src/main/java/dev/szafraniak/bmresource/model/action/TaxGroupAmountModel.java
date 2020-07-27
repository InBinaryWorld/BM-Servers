package dev.szafraniak.bmresource.model.action;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxGroupAmountModel {
    public TaxGroupAmountModel(BigDecimal net, BigDecimal tax, BigDecimal gross, BigDecimal taxRate) {
        this.net = net;
        this.tax = tax;
        this.gross = gross;
        this.taxRate = taxRate;
    }

    private BigDecimal net;
    private BigDecimal tax;
    private BigDecimal gross;
    private BigDecimal taxRate;
}
