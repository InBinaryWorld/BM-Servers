package dev.szafraniak.bmresource.model.action.stats;

import lombok.Data;

@Data
public class CompanyStatsModel {

    private ProductStatsModel products;
    private FinancesStatsModel finances;
    private InvoicesStatsModel invoices;
}
