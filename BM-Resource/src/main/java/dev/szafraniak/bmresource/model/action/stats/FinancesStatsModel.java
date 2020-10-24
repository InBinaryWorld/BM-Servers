package dev.szafraniak.bmresource.model.action.stats;

import dev.szafraniak.bmresource.config.BaseEnvironment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancesStatsModel {

    private int EventsNumber;
    private BigDecimal currentState;
    private BigDecimal totalIncome;
    private BigDecimal totalOutcome;
    private BigDecimal lastChange;
    private String currency = BaseEnvironment.CURRENCY;
}
