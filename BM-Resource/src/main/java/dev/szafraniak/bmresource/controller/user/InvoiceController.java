package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.services.entity.InvoiceService;
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

    private InvoiceService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<InvoiceGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkInvoice(#companyId, #entityId)")
    public InvoiceGetDTO getEntity(@PathVariable Long companyId,
                                   @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @Autowired
    public void setInvoiceService(InvoiceService invoiceService) {
        this.service = invoiceService;
    }
}

