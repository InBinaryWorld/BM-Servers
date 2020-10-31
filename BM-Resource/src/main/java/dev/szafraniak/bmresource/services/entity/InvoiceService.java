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
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class InvoiceService extends AbstractCompanyService<Invoice, InvoiceRepository, InvoiceConverter, InvoiceGetDTO, InvoicePostDTO, InvoicePutDTO> {


    protected FileService fileService;
    protected InvoiceActionService actionService;
    protected FinancialRowService financialRowService;

    @Autowired
    public InvoiceService(InvoiceConverter converter, InvoiceRepository repository) {
        super(converter, repository);
    }

    public InvoiceGetDTO createInvoiceAction(CreateInvoiceDTO dto, Long companyId) throws Exception {
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

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public byte[] getInvoicePdf(Long entityId) throws IOException {
        Invoice entity = repository.findById(entityId).get();
        String filePath = fileService.getInvoicePath(entity.getFileReference());
        InputStream in = new FileInputStream(filePath);
        return in.readAllBytes();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public InvoiceGetDTO paidOffAction(Long entityId) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        Invoice invoice = repository.findById(entityId).get();
        invoice.setIsPaid(true);
        invoice.setDateOfPayment(now);
        Invoice saved = repository.save(invoice);
        financialRowService.createFromInvoice(saved, now);
        return converter.convertToDTO(saved);
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setFinancialRowService(FinancialRowService financialRowService) {
        this.financialRowService = financialRowService;
    }

    @Autowired
    public void setActionService(InvoiceActionService actionService) {
        this.actionService = actionService;
    }
}
