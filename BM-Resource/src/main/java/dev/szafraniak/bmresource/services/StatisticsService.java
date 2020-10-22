package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.model.action.AmountModel;
import dev.szafraniak.bmresource.model.action.PriceModel;
import dev.szafraniak.bmresource.model.action.stats.CompanyStatsModel;
import dev.szafraniak.bmresource.model.action.stats.FinancesStatsModel;
import dev.szafraniak.bmresource.model.action.stats.InvoicesStatsModel;
import dev.szafraniak.bmresource.model.action.stats.ProductStatsModel;
import dev.szafraniak.bmresource.model.entity.*;
import dev.szafraniak.bmresource.services.entity.CompanyService;
import dev.szafraniak.bmresource.utils.FinancialUtils;
import dev.szafraniak.bmresource.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    CompanyService companyService;

    public CompanyStatsModel getCompanyStatistic(Long companyId) {
        Company company = companyService.getEntity(companyId);
        CompanyStatsModel model = new CompanyStatsModel();
        model.setFinances(getFinancesStats(company));
        model.setProducts(getProductsStats(company));
        model.setInvoices(getInvoicesStats(company));
        return model;
    }

    public FinancesStatsModel getFinancesStats(Long companyId) {
        Company company = companyService.getEntity(companyId);
        return getFinancesStats(company);
    }

    public ProductStatsModel getProductsStats(Long companyId) {
        Company company = companyService.getEntity(companyId);
        return getProductsStats(company);
    }

    public InvoicesStatsModel getInvoicesStats(Long companyId) {
        Company company = companyService.getEntity(companyId);
        return getInvoicesStats(company);
    }


    private InvoicesStatsModel getInvoicesStats(Company company) {
        List<Invoice> invoices = company.getInvoices();
        List<Invoice> paidInvoices = ListUtils.filter(invoices, Invoice::getIsPaid);
        List<Invoice> unpaidInvoices = ListUtils.filter(invoices, inv -> !inv.getIsPaid());

        BigDecimal unpaidValue = FinancialUtils.sumBy(unpaidInvoices, invoice -> invoice.getTotalAmount().getGross());
        BigDecimal paidValue = FinancialUtils.sumBy(paidInvoices, invoice -> invoice.getTotalAmount().getGross());

        InvoicesStatsModel model = new InvoicesStatsModel();
        model.setInvoicesNumber(invoices.size());
        model.setPaid(paidInvoices.size());
        model.setUnpaid(unpaidInvoices.size());
        model.setPaidValue(paidValue);
        model.setUnpaidValue(unpaidValue);
        return model;
    }

    private ProductStatsModel getProductsStats(Company company) {
        List<Product> products = company.getProducts();
        BigDecimal totalGross = countProductsGrossValue(products);
        ProductStatsModel model = new ProductStatsModel();
        model.setTotalGrossValue(totalGross);
        return model;
    }

    private FinancesStatsModel getFinancesStats(Company company) {
        List<FinancialRow> events = company.getFinancialHistory();
        int eventsNumber = events.size();
        BigDecimal currentState = FinancialUtils.sumBy(events, FinancialRow::getAmountChange);

        FinancesStatsModel model = new FinancesStatsModel();
        model.setEventsNumber(eventsNumber);
        model.setCurrentState(currentState);
        return model;
    }

    private BigDecimal countProductsGrossValue(List<Product> products) {
        List<AmountModel> productAmounts = products.stream().map(product -> {
            Price price = product.getProductModel().getPriceSuggestion();
            BigDecimal quantity = product.getQuantity();
            return FinancialUtils.countAmount(new PriceModel(price), quantity);
        }).collect(Collectors.toList());
        return FinancialUtils.sumBy(productAmounts, AmountModel::getGross);
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
