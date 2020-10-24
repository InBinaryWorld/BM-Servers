package dev.szafraniak.bmresource.model.action.stats;

import dev.szafraniak.bmresource.config.BaseEnvironment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResourcesStatsModel {

    private String currency = BaseEnvironment.CURRENCY;
    private BigDecimal totalGrossValue;
    private int warehousesNumber;
    private int productsNumber;
    private int productModelsNumber;
    private int serviceModelsNumber;
}
