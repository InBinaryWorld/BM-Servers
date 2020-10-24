package dev.szafraniak.bmresource.controller.entity;

import dev.szafraniak.bmresource.dto.action.createInvoice.CreateInvoiceDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.services.entity.InvoiceService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api/companies/{companyId}/invoices")
public class InvoiceController {

    private InvoiceService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<InvoiceGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAllDTO(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public InvoiceGetDTO create(@PathVariable Long companyId,
                                @Valid @RequestBody CreateInvoiceDTO dto) throws Exception {
        return service.createInvoiceAction(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkInvoice(#companyId, #entityId)")
    public InvoiceGetDTO getEntity(@PathVariable Long companyId,
                                   @PathVariable Long entityId) {
        return service.getEntityDTO(entityId);
    }

    @GetMapping(
            value = "/{entityId}/document",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @PreAuthorize("@permissionChecker.checkInvoice(#companyId, #entityId)")
    public @ResponseBody
    byte[] getInvoicePdf(@PathVariable Long companyId,
                         @PathVariable Long entityId) throws IOException {
        return service.getInvoicePdf(entityId);
    }

    @PutMapping("/{entityId}/action/paid")
    @PreAuthorize("@permissionChecker.checkInvoice(#companyId, #entityId)")
    public InvoiceGetDTO paidOffAction(@PathVariable Long companyId,
                                       @PathVariable Long entityId) {
        return service.paidOffAction(entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkInvoice(#companyId, #entityId)")
    public ResponseEntity<Void> delete(@PathVariable Long companyId,
                                       @PathVariable Long entityId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(InvoiceService service) {
        this.service = service;
    }
}

