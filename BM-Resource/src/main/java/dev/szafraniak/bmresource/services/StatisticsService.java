package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.model.action.invoice.AmountModel;
import dev.szafraniak.bmresource.model.action.invoice.PriceModel;
import dev.szafraniak.bmresource.model.action.stats.CompanyStatsModel;
import dev.szafraniak.bmresource.model.action.stats.FinancesStatsModel;
import dev.szafraniak.bmresource.model.action.stats.InvoicesStatsModel;
import dev.szafraniak.bmresource.model.action.stats.ResourcesStatsModel;
import dev.szafraniak.bmresource.model.entity.*;
import dev.szafraniak.bmresource.services.entity.CompanyService;
import dev.szafraniak.bmresource.utils.FinancialUtils;
import dev.szafraniak.bmresource.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    CompanyService companyService;

    public CompanyStatsModel getCompanyStatistic(Long companyId) {
        Company company = companyService.getEntity(companyId);
        CompanyStatsModel model = new CompanyStatsModel();
        model.setFinances(getFinancesStats(company));
        model.setResources(getResourcesStats(company));
        model.setInvoices(getInvoicesStats(company));
        return model;
    }

    public FinancesStatsModel getFinancesStats(Long companyId) {
        Company company = companyService.getEntity(companyId);
        return getFinancesStats(company);
    }

    public ResourcesStatsModel getResourcesStats(Long companyId) {
        Company company = companyService.getEntity(companyId);
        return getResourcesStats(company);
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

        BigDecimal lastInvoiceAmount = invoices.stream().max(Comparator.comparing(Invoice::getCreationDate))
                .map(Invoice::getTotalAmount).map(Amount::getGross).orElse(null);

        InvoicesStatsModel model = new InvoicesStatsModel();
        model.setInvoicesNumber(invoices.size());
        model.setPaid(paidInvoices.size());
        model.setUnpaid(unpaidInvoices.size());
        model.setPaidValue(paidValue);
        model.setUnpaidValue(unpaidValue);
        model.setLastInvoiceValue(lastInvoiceAmount);
        return model;
    }

    private ResourcesStatsModel getResourcesStats(Company company) {
        List<ProductModel> productModels = company.getProductModels();
        List<ServiceModel> serviceModels = company.getServiceModels();
        List<Warehouse> warehouses = company.getWarehouses();
        List<Product> products = company.getProducts();

        BigDecimal totalGross = countProductsGrossValue(products);
        ResourcesStatsModel model = new ResourcesStatsModel();
        model.setTotalGrossValue(totalGross);
        model.setProductModelsNumber(productModels.size());
        model.setProductsNumber(products.size());
        model.setServiceModelsNumber(serviceModels.size());
        model.setWarehousesNumber(warehouses.size());
        return model;
    }

    private FinancesStatsModel getFinancesStats(Company company) {
        List<FinancialRow> events = company.getFinancialHistory();
        List<BigDecimal> changes = ListUtils.map(events, FinancialRow::getAmountChange);
        List<BigDecimal> incomeList = ListUtils.filter(changes, amount -> amount.compareTo(BigDecimal.ZERO) > 0);
        List<BigDecimal> outcomeList = ListUtils.filter(changes, amount -> amount.compareTo(BigDecimal.ZERO) < 0);

        BigDecimal totalIncome = FinancialUtils.sumBy(incomeList, amount -> amount);
        BigDecimal totalOutcome = FinancialUtils.sumBy(outcomeList, amount -> amount).abs();
        BigDecimal currentState = totalIncome.subtract(totalOutcome);

        BigDecimal lastChange = events.stream().max(Comparator.comparing(FinancialRow::getEventDate))
                .map(FinancialRow::getAmountChange).orElse(null);

        FinancesStatsModel model = new FinancesStatsModel();
        model.setEventsNumber(events.size());
        model.setCurrentState(currentState);
        model.setTotalIncome(totalIncome);
        model.setTotalOutcome(totalOutcome);
        model.setLastChange(lastChange);
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
