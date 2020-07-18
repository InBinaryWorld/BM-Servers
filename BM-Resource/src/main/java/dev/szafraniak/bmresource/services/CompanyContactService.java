package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.CompanyContactConverter;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactGetDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPostDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPutDTO;
import dev.szafraniak.bmresource.entity.CompanyContact;
import dev.szafraniak.bmresource.repository.CompanyContactRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyContactService {

    private CompanyContactConverter companyContactConverter;
    private CompanyContactRepository companyContactRepository;


    public BmCollection<CompanyContactGetDTO> getContacts(Long companyId) {
        return companyContactRepository
                .findAllByCompany_Id(companyId).stream()
                .map(companyContactConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public CompanyContactGetDTO getContact(Long companyContactId) {
        CompanyContact companyContact = companyContactRepository.findById(companyContactId).get();
        return companyContactConverter.convertToDTO(companyContact);
    }

    public CompanyContactGetDTO createContact(CompanyContactPostDTO dto, Long companyId) {
        CompanyContact companyContact = companyContactConverter.convertFromDTO(dto, companyId);
        CompanyContact saved = companyContactRepository.save(companyContact);
        return companyContactConverter.convertToDTO(saved);
    }

    public CompanyContactGetDTO updateContact(CompanyContactPutDTO dto, Long companyContactId) {
        CompanyContact contact = companyContactConverter.convertFromDTO(dto, companyContactId);
        CompanyContact saved = companyContactRepository.save(contact);
        return companyContactConverter.convertToDTO(saved);
    }

    public boolean deleteContact(Long productModelId) {
        companyContactRepository.deleteById(productModelId);
        return true;
    }

    @Autowired
    public void setCompanyContactConverter(CompanyContactConverter companyContactConverter) {
        this.companyContactConverter = companyContactConverter;
    }

    @Autowired
    public void setCompanyContactRepository(CompanyContactRepository companyContactRepository) {
        this.companyContactRepository = companyContactRepository;
    }
}
