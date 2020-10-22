package dev.szafraniak.bmresource.model.action;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AmountModel {
    public AmountModel(BigDecimal net, BigDecimal tax, BigDecimal gross) {
        this.net = net;
        this.tax = tax;
        this.gross = gross;
    }

    private BigDecimal net;
    private BigDecimal tax;
    private BigDecimal gross;
}
