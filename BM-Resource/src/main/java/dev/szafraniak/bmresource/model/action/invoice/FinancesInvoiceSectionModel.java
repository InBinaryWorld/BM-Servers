package dev.szafraniak.bmresource.model.action.invoice;

import lombok.Data;

import java.util.List;

@Data
public class FinancesInvoiceSectionModel {

    public FinancesInvoiceSectionModel(AmountModel totalAmount, List<InvoiceOrderItemModel> items, List<TaxGroupAmountModel> taxGroups) {
        this.totalAmount = totalAmount;
        this.taxGroups = taxGroups;
        this.items = items;
    }

    private AmountModel totalAmount;
    private List<InvoiceOrderItemModel> items;
    private List<TaxGroupAmountModel> taxGroups;
}
