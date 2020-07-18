package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.InvoiceConverter;
import dev.szafraniak.bmresource.dto.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.entity.Invoice;
import dev.szafraniak.bmresource.repository.InvoiceRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private InvoiceConverter invoiceConverter;

    public BmCollection<InvoiceGetDTO> getInvoices(Long companyId) {
        return invoiceRepository
                .findAllByCompany_Id(companyId).stream()
                .map(invoiceConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public InvoiceGetDTO getInvoice(Long invoiceId) {
        Invoice saved = invoiceRepository.findById(invoiceId).get();
        return invoiceConverter.convertToDTO(saved);
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Autowired
    public void setInvoiceConverter(InvoiceConverter invoiceConverter) {
        this.invoiceConverter = invoiceConverter;
    }
}
