package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.amount.AmountGetDTO;
import dev.szafraniak.bmresource.dto.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.dto.shared.BaseGetDTO;
import dev.szafraniak.bmresource.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter {

    public AmountConverter amountConverter;
    public ContactConverter contactConverter;

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
        ;
        return dto;
    }

    @Autowired
    public void setAmountConverter(AmountConverter amountConverter) {
        this.amountConverter = amountConverter;
    }

    @Autowired
    public void setContactConverter(ContactConverter contactConverter) {
        this.contactConverter = contactConverter;
    }
}
