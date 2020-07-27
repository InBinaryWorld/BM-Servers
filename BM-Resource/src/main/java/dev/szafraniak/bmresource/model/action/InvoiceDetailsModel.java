package dev.szafraniak.bmresource.model.action;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class InvoiceDetailsModel {
    public InvoiceDetailsModel(BigDecimal totalGross, BigDecimal totalTax, BigDecimal totalNet,
                               List<InvoiceOrderItemModel> items, List<TaxGroupAmountModel> taxGroups) {
        this.totalGross = totalGross;
        this.totalTax = totalTax;
        this.totalNet = totalNet;
        this.taxGroups = taxGroups;
        this.items = items;
    }

    private BigDecimal totalGross;
    private BigDecimal totalTax;
    private BigDecimal totalNet;
    private List<InvoiceOrderItemModel> items;
    private List<TaxGroupAmountModel> taxGroups;
}
