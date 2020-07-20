package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.converters.interfaces.ConverterCompanyInterface;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.entity.IndividualContact;
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
        IndividualContact individual = individualContactRepository.findById(individualId).get();
        contactConverter.fullFillFromDTO(individual, dto);
        individual.setFirstName(dto.getFirstName());
        individual.setLastName(dto.getLastName());
        individual.setEmployee(null);
        return individual;
    }

    @Autowired
    public void setIndividualContactRepository(IndividualContactRepository individualContactRepository) {
        this.individualContactRepository = individualContactRepository;
    }

    @Autowired
    public void setContactConverter(ContactConverter contactConverter) {
        this.contactConverter = contactConverter;
    }
}
