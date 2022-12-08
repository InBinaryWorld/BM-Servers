package dev.szafraniak.bmresource.model.action.invoice;

import dev.szafraniak.bmresource.model.action.contact.InvoiceContactModel;
import dev.szafraniak.bmresource.model.entity.payment.PaymentMethod;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class BaseInvoiceDataModel {

    private InvoiceContactModel buyer;
    private InvoiceContactModel seller;
    private InvoiceContactModel receiver;
    private OffsetDateTime creationDate;
    private PaymentMethod paymentMethod;
    private String InvoiceNumber;
    private String InvoiceLogo;
    private LocalDate dueDate;
}
