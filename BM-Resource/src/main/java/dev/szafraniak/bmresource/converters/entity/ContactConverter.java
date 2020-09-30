package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.entity.contact.ContactGetDTO;
import dev.szafraniak.bmresource.dto.entity.contact.ContactPostDTO;
import dev.szafraniak.bmresource.dto.entity.contact.ContactPutDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoiceGetDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import dev.szafraniak.bmresource.model.entity.Address;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.contact.Contact;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactConverter {

    private AddressConverter addressConverter;
    private InvoiceConverter invoiceConverter;
    private CompanyRepository companyRepository;


    public ContactGetDTO convertToDTO(Contact contact) {
        if (contact == null) {
            return null;
        }
        ContactGetDTO contactDTO = new ContactGetDTO();
        fullFillDTO(contact, contactDTO);
        return contactDTO;
    }

    public BaseGetDTO convertToBaseDTO(Contact contact) {
        if (contact == null) {
            return null;
        }
        BaseGetDTO dto = new BaseGetDTO();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        return dto;
    }

    public void fullFillDTO(Contact contact, ContactGetDTO dto) {
        if (contact == null) {
            return;
        }
        AddressGetDTO addressGetDTO = addressConverter.convertToDTO(contact.getAddress());
        List<InvoiceGetDTO> invoices = contact.getInvoices().stream()
                .map(invoiceConverter::convertToDTO)
                .collect(Collectors.toList());
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setAddress(addressGetDTO);
        dto.setInvoices(invoices);
        dto.setPhone(contact.getPhone());
    }

    public void fullFillFromDTO(Contact contact, ContactPostDTO dto, Long companyId) {
        if (dto == null) {
            return;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Company company = companyRepository.findById(companyId).get();
        Address address = addressConverter.convertFromDTO(dto.getAddress());
        contact.setAddress(address);
        contact.setInvoices(new ArrayList<>());
        contact.setPhone(dto.getPhone());
        contact.setCompany(company);
    }

    public void fullFillFromDTO(Contact contact, ContactPutDTO dto) {
        if (dto == null) {
            return;
        }
        Long addressId = contact.getAddress().getId();
        Address address = addressConverter.convertFromDTO(dto.getAddress(), addressId);
        contact.setAddress(address);
        contact.setPhone(dto.getPhone());
    }

    @Autowired
    public void setAddressConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Autowired
    public void setInvoiceConverter(InvoiceConverter invoiceConverter) {
        this.invoiceConverter = invoiceConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
