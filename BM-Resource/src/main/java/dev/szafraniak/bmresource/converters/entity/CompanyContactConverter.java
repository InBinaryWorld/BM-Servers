package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.companyContact.CompanyContactGetDTO;
import dev.szafraniak.bmresource.dto.entity.companyContact.CompanyContactPostDTO;
import dev.szafraniak.bmresource.dto.entity.companyContact.CompanyContactPutDTO;
import dev.szafraniak.bmresource.model.entity.CompanyContact;
import dev.szafraniak.bmresource.repository.entity.CompanyContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyContactConverter implements ConverterCompanyInterface<CompanyContact, CompanyContactGetDTO, CompanyContactPostDTO, CompanyContactPutDTO> {

    private ContactConverter contactConverter;
    private CompanyContactRepository companyContactRepository;


    public CompanyContactGetDTO convertToDTO(CompanyContact companyContact) {
        if (companyContact == null) {
            return null;
        }
        CompanyContactGetDTO dto = new CompanyContactGetDTO();
        contactConverter.fullFillDTO(companyContact, dto);
        dto.setTaxIdentityNumber(companyContact.getTaxIdentityNumber());
        dto.setName(companyContact.getName());
        return dto;
    }

    public CompanyContact convertFromDTO(CompanyContactPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        CompanyContact companyContact = new CompanyContact();
        contactConverter.fullFillFromDTO(companyContact, dto, companyId);
        companyContact.setTaxIdentityNumber(dto.getTaxIdentityNumber());
        companyContact.setName(dto.getName());
        return companyContact;
    }

    public CompanyContact convertFromDTO(CompanyContactPutDTO dto, Long companyContactId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        CompanyContact companyContact = companyContactRepository.findById(companyContactId).get();
        contactConverter.fullFillFromDTO(companyContact, dto);
        companyContact.setTaxIdentityNumber(dto.getTaxIdentityNumber());
        companyContact.setName(dto.getName());
        return companyContact;
    }

    @Autowired
    public void setContactConverter(ContactConverter contactConverter) {
        this.contactConverter = contactConverter;
    }

    @Autowired
    public void setCompanyContactRepository(CompanyContactRepository companyContactRepository) {
        this.companyContactRepository = companyContactRepository;
    }
}
