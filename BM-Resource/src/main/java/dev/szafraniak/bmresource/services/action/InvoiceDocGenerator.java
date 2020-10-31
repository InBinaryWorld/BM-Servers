package dev.szafraniak.bmresource.services.action;

import dev.szafraniak.bmresource.model.action.invoice.BaseInvoiceDataModel;
import dev.szafraniak.bmresource.model.action.invoice.FinancesInvoiceSectionModel;

public interface InvoiceDocGenerator {
    void createInvoice(BaseInvoiceDataModel model, FinancesInvoiceSectionModel details, String outputFile) throws Exception;
}
