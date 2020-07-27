package dev.szafraniak.bmresource.services.action;

import dev.szafraniak.bmresource.model.action.CreateInvoiceModel;
import dev.szafraniak.bmresource.model.action.InvoiceDetailsModel;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDocGeneratorImpl implements InvoiceDocGenerator {
    @Override
    public String createInvoice(CreateInvoiceModel model, InvoiceDetailsModel options) {
        return "test.pdf";
    }
}
