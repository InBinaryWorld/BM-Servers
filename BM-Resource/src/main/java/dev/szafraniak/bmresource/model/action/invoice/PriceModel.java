package dev.szafraniak.bmresource.model.action.invoice;

import dev.szafraniak.bmresource.model.entity.Price;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceModel {
    public PriceModel(BigDecimal net, BigDecimal taxRate, BigDecimal gross) {
        this.net = net;
        this.taxRate = taxRate;
        this.gross = gross;
    }

    public PriceModel(Price price) {
        this.net = price.getNet();
        this.taxRate = price.getTaxRate();
        this.gross = price.getGross();
    }

    private BigDecimal net;
    private BigDecimal taxRate;
    private BigDecimal gross;
}
