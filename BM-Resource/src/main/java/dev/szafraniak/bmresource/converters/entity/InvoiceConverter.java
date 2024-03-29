package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.amount.AmountGetDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePutDTO;
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
    private CompanyRepository companyRepository;
    private InvoiceRepository invoiceRepository;

    public InvoiceGetDTO convertToDTO(Invoice invoice) {
        if (invoice == null) {
            return null;
        }
        AmountGetDTO amountGetDTO = amountConverter.convertToDTO(invoice.getTotalAmount());
        InvoiceGetDTO dto = new InvoiceGetDTO();
        dto.setId(invoice.getId());
        dto.setBuyerName(invoice.getBuyerName());
        dto.setCreationDate(invoice.getCreationDate());
        dto.setSplitPayment(invoice.getSplitPayment());
        dto.setDueDate(invoice.getDueDate());
        dto.setIssueDate(invoice.getIssueDate());
        dto.setSellDate(invoice.getSellDate());
        dto.setFileReference(invoice.getFileReference());
        dto.setInvoiceName(invoice.getInvoiceName());
        dto.setIsPaid(invoice.getIsPaid());
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
        invoice.setCreationDate(dto.getCreationDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setIssueDate(dto.getIssueDate());
        invoice.setSellDate(dto.getSellDate());
        invoice.setSplitPayment(dto.getSplitPayment());
        invoice.setFileReference(dto.getFileReference());
        invoice.setInvoiceName(dto.getInvoiceName());
        invoice.setIsPaid(dto.getIsPaid());
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
        invoice.setIsPaid(dto.getIsPaid());
        return invoice;

    }

    @Autowired
    public void setAmountConverter(AmountConverter amountConverter) {
        this.amountConverter = amountConverter;
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
