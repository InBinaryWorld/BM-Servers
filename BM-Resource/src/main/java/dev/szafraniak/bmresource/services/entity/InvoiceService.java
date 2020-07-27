package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.InvoiceConverter;
import dev.szafraniak.bmresource.dto.action.createInvoice.CreateInvoiceDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePutDTO;
import dev.szafraniak.bmresource.model.entity.Invoice;
import dev.szafraniak.bmresource.repository.entity.InvoiceRepository;
import dev.szafraniak.bmresource.services.FileService;
import dev.szafraniak.bmresource.services.action.InvoiceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService extends AbstractCompanyService<Invoice, InvoiceRepository, InvoiceConverter, InvoiceGetDTO, InvoicePostDTO, InvoicePutDTO> {


    protected FileService fileService;
    protected InvoiceActionService actionService;

    @Autowired
    public InvoiceService(InvoiceConverter converter, InvoiceRepository repository) {
        super(converter, repository);
    }

    public InvoiceGetDTO createInvoiceAction(CreateInvoiceDTO dto, Long companyId) {
        InvoicePostDTO invoicePostDTO = actionService.generateInvoice(dto, companyId);
        return this.createFromDTO(invoicePostDTO, companyId);
    }

    @Override
    public boolean delete(Long entityId) {
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Invoice invoice = repository.findById(entityId).get();
        fileService.removeInvoice(invoice.getFileReference());
        return super.delete(entityId);
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setActionService(InvoiceActionService actionService) {
        this.actionService = actionService;
    }
}
