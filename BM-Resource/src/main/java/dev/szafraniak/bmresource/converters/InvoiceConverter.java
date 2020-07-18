package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.entity.Invoice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConverter {

    private ModelMapper modelMapper;

    public InvoiceGetDTO convertToDTO(Invoice invoice) {
        InvoiceGetDTO dto = modelMapper.map(invoice, InvoiceGetDTO.class);
        dto.getContact().setName(invoice.getContact().getName());
        return dto;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
