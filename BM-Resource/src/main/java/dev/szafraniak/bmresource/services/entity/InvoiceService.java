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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class InvoiceService extends AbstractCompanyService<Invoice, InvoiceRepository, InvoiceConverter, InvoiceGetDTO, InvoicePostDTO, InvoicePutDTO> {


    protected FileService fileService;
    protected InvoiceActionService actionService;

    @Autowired
    public InvoiceService(InvoiceConverter converter, InvoiceRepository repository) {
        super(converter, repository);
    }

    public InvoiceGetDTO createInvoiceAction(CreateInvoiceDTO dto, Long companyId) throws Exception {
        String fileReference = getNewInvoiceFileReference(dto, companyId);
        InvoicePostDTO invoicePostDTO = actionService.generateInvoice(dto, companyId, fileReference);
        return this.createFromDTO(invoicePostDTO, companyId);
    }

    @Override
    public boolean delete(Long entityId) {
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Invoice invoice = repository.findById(entityId).get();
        fileService.removeInvoice(invoice.getFileReference());
        return super.delete(entityId);
    }

    private String getNewInvoiceFileReference(CreateInvoiceDTO dto, Long companyId) {
        String uuid = UUID.randomUUID().toString();
        long secondsPart = dto.getCreationDate().toEpochSecond();
        return String.format("%d#%d#%s", companyId, secondsPart, uuid);
    }

    public byte[] getInvoicePdf(Long entityId) throws IOException {
        Invoice entity = repository.findById(entityId).get();
        String filePath = fileService.getInvoicePath(entity.getFileReference());
        InputStream in = new FileInputStream(filePath);
        return in.readAllBytes();
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
