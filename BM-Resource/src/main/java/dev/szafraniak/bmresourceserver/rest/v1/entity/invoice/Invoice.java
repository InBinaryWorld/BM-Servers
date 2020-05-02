package dev.szafraniak.bmresourceserver.rest.v1.entity.invoice;

import dev.szafraniak.bmresourceserver.rest.v1.entity.user.Company;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Invoice {
    private String id;
    private String invoiceRef;
    // only for attach invoice to company
    private Company owner;
    // Maybe replace it by sth like List<String> sellerDataRows , buyerDataRows
    private String sellerName;
    private String sellerNip;
    private String buyerName;
    private String buyerNip;
    private String state;
    private String currency;
    private FileRefference file;
    private BigDecimal grossAmount;
    private Date dueDate;
    private Date creationDateTime;
    private List<InvoiceItem> invoiceItems;

}
