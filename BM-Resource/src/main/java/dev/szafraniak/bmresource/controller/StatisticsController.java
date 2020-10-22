package dev.szafraniak.bmresource.controller;

import dev.szafraniak.bmresource.model.action.stats.CompanyStatsModel;
import dev.szafraniak.bmresource.model.action.stats.FinancesStatsModel;
import dev.szafraniak.bmresource.model.action.stats.InvoicesStatsModel;
import dev.szafraniak.bmresource.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stats/company/{companyId}")
public class StatisticsController {

    private StatisticsService statisticsService;

    @GetMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public CompanyStatsModel getCompanyStats(@PathVariable Long companyId) {
        return statisticsService.getCompanyStatistic(companyId);
    }

    @GetMapping("/invoices")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public InvoicesStatsModel getInvoicesStats(@PathVariable Long companyId) {
        return statisticsService.getInvoicesStats(companyId);
    }

    @GetMapping("/finances")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public FinancesStatsModel getFinancesStats(@PathVariable Long companyId) {
        return statisticsService.getFinancesStats(companyId);
    }


    @Autowired
    public void setAuthService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
}