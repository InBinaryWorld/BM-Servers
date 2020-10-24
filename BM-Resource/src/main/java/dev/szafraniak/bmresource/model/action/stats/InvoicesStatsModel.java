package dev.szafraniak.bmresource.model.action.stats;

import dev.szafraniak.bmresource.config.BaseEnvironment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoicesStatsModel {

    private int invoicesNumber;
    private int unpaid;
    private int paid;
    private BigDecimal unpaidValue;
    private BigDecimal paidValue;
    private BigDecimal lastInvoiceValue;
    private String currency = BaseEnvironment.CURRENCY;
}
