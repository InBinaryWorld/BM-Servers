package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.InvoiceConverter;
import dev.szafraniak.bmresource.dto.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.dto.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.dto.invoice.InvoicePutDTO;
import dev.szafraniak.bmresource.entity.Invoice;
import dev.szafraniak.bmresource.repository.entity.InvoiceRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService extends AbstractCompanyService<Invoice, InvoiceRepository, InvoiceConverter, InvoiceGetDTO, InvoicePostDTO, InvoicePutDTO> {

    @Autowired
    public InvoiceService(InvoiceConverter converter, InvoiceRepository repository) {
        super(converter, repository);
    }
}
