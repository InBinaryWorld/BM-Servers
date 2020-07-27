package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.amount.AmountGetDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePutDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import dev.szafraniak.bmresource.model.entity.Amount;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.Invoice;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter implements ConverterCompanyInterface<Invoice, InvoiceGetDTO, InvoicePostDTO, InvoicePutDTO> {

    private AmountConverter amountConverter;
    private ContactConverter contactConverter;
    private CompanyRepository companyRepository;
    private InvoiceRepository invoiceRepository;

    public InvoiceGetDTO convertToDTO(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        AmountGetDTO amountGetDTO = amountConverter.convertToDTO(invoice.getTotalAmount());
        BaseGetDTO contact = contactConverter.convertToBaseDTO(invoice.getContact());
        InvoiceGetDTO dto = new InvoiceGetDTO();
        dto.setId(invoice.getId());
        dto.setBuyerName(invoice.getBuyerName());
        dto.setContact(contact);
        dto.setCreationDate(invoice.getCreationDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setFileReference(invoice.getFileReference());
        dto.setInvoiceName(invoice.getInvoiceName());
        dto.setState(invoice.getState());
        dto.setTotalAmount(amountGetDTO);
        return dto;
    }

    @Override
    public Invoice convertFromDTO(InvoicePostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Company company = companyRepository.findById(companyId).get();
        Invoice invoice = new Invoice();
        Amount amount = amountConverter.convertFromDTO(dto.getTotalAmount());
        invoice.setBuyerName(dto.getBuyerName());
        invoice.setContact(dto.getContact());
        invoice.setCreationDate(dto.getCreationDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setFileReference(dto.getFileReference());
        invoice.setInvoiceName(dto.getInvoiceName());
        invoice.setState(dto.getState());
        invoice.setTotalAmount(amount);
        invoice.setCompany(company);
        return invoice;
    }

    @Override
    public Invoice convertFromDTO(InvoicePutDTO dto, Long entityId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Invoice invoice = invoiceRepository.findById(entityId).get();
        invoice.setState(dto.getState());
        return invoice;

    }

    @Autowired
    public void setAmountConverter(AmountConverter amountConverter) {
        this.amountConverter = amountConverter;
    }

    @Autowired
    public void setContactConverter(ContactConverter contactConverter) {
        this.contactConverter = contactConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setInvoiceRepository(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
