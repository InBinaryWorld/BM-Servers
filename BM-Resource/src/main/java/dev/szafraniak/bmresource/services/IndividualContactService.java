package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.IndividualContactConverter;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.entity.IndividualContact;
import dev.szafraniak.bmresource.repository.IndividualContactRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividualContactService {

    private IndividualContactConverter individualContactConverter;
    private IndividualContactRepository individualContactRepository;


    public BmCollection<IndividualContactGetDTO> getContacts(Long companyId) {
        return individualContactRepository
                .findAllByCompany_Id(companyId).stream()
                .map(individualContactConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public IndividualContactGetDTO getContact(Long companyContactId) {
        IndividualContact contact = individualContactRepository.findById(companyContactId).get();
        return individualContactConverter.convertToDTO(contact);
    }

    public IndividualContactGetDTO createContact(IndividualContactPostDTO dto, Long companyId) {
        IndividualContact contact = individualContactConverter.convertFromDTO(dto, companyId);
        IndividualContact saved = individualContactRepository.save(contact);
        return individualContactConverter.convertToDTO(saved);
    }

    public IndividualContactGetDTO updateContact(IndividualContactPutDTO dto, Long contactId) {
        IndividualContact contact = individualContactConverter.convertFromDTO(dto, contactId);
        IndividualContact saved = individualContactRepository.save(contact);
        return individualContactConverter.convertToDTO(saved);
    }

    public boolean deleteContact(Long productModelId) {
        individualContactRepository.deleteById(productModelId);
        return true;
    }

    @Autowired
    public void setIndividualContactConverter(IndividualContactConverter individualContactConverter) {
        this.individualContactConverter = individualContactConverter;
    }

    @Autowired
    public void setIndividualContactRepository(IndividualContactRepository individualContactRepository) {
        this.individualContactRepository = individualContactRepository;
    }
}
