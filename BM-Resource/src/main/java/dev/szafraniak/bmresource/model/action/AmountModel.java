package dev.szafraniak.bmresource.model.action;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AmountModel {

    private BigDecimal net;
    private BigDecimal tax;
    private BigDecimal gross;
}
