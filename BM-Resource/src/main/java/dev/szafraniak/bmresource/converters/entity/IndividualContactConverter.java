package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.entity.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.entity.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.model.entity.IndividualContact;
import dev.szafraniak.bmresource.repository.entity.IndividualContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndividualContactConverter implements ConverterCompanyInterface<IndividualContact,
        IndividualContactGetDTO, IndividualContactPostDTO, IndividualContactPutDTO> {

    private ContactConverter contactConverter;
    private IndividualContactRepository individualContactRepository;


    public IndividualContactGetDTO convertToDTO(IndividualContact individualContact) {
        if (individualContact == null) {
            return null;
        }
        IndividualContactGetDTO dto = new IndividualContactGetDTO();
        contactConverter.fullFillDTO(individualContact, dto);
        dto.setFirstName(individualContact.getFirstName());
        dto.setLastName(individualContact.getLastName());
        return dto;
    }

    public IndividualContact convertFromDTO(IndividualContactPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        IndividualContact individual = new IndividualContact();
        contactConverter.fullFillFromDTO(individual, dto, companyId);
        individual.setFirstName(dto.getFirstName());
        individual.setLastName(dto.getLastName());
        individual.setEmployee(null);
        return individual;
    }

    public IndividualContact convertFromDTO(IndividualContactPutDTO dto, Long individualId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        IndividualContact individual = individualContactRepository.findById(individualId).get();
        contactConverter.fullFillFromDTO(individual, dto);
        individual.setFirstName(dto.getFirstName());
        individual.setLastName(dto.getLastName());
        individual.setEmployee(null);
        return individual;
    }

    @Autowired
    public void setContactConverter(ContactConverter contactConverter) {
        this.contactConverter = contactConverter;
    }

    @Autowired
    public void setIndividualContactRepository(IndividualContactRepository individualContactRepository) {
        this.individualContactRepository = individualContactRepository;
    }
}
