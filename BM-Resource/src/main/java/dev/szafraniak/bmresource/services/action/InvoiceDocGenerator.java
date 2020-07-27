package dev.szafraniak.bmresource.services.action;

import dev.szafraniak.bmresource.model.action.CreateInvoiceModel;
import dev.szafraniak.bmresource.model.action.InvoiceDetailsModel;

public interface InvoiceDocGenerator {
    String createInvoice(CreateInvoiceModel model, InvoiceDetailsModel options);
}
