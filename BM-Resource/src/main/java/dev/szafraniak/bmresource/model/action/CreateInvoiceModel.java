package dev.szafraniak.bmresource.model.action;

import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class CreateInvoiceModel {

    private InvoiceContactModel buyer;
    private InvoiceContactModel seller;
    private InvoiceContactModel receiver;
    private String InvoiceNumber;
    private String bankAccount;
    private LocalDate dueDate;
    private OffsetDateTime creationDate;
}
