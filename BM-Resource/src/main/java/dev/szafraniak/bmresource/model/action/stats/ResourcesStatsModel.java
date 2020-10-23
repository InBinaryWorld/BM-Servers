package dev.szafraniak.bmresource.model.action.stats;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResourcesStatsModel {

    private BigDecimal totalGrossValue;
    private int warehousesNumber;
    private int productsNumber;
    private int productModelsNumber;
    private int serviceModelsNumber;
}
