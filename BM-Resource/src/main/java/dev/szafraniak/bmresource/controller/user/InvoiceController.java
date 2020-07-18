package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.services.InvoiceService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/companies/{companyId}/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<InvoiceGetDTO> getInvoices(@PathVariable Long companyId) {
        return invoiceService.getInvoices(companyId);
    }

    @GetMapping("/{invoiceId}")
    @PreAuthorize("@permissionChecker.checkInvoice(#companyId, #invoiceId)")
    public InvoiceGetDTO getInvoice(@PathVariable Long companyId,
                                    @PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
}

