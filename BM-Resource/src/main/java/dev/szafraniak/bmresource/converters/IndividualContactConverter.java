package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.entity.IndividualContact;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.repository.IndividualContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndividualContactConverter {

    private ModelMapper modelMapper;
    private AddressConverter addressConverter;
    private ContactConverter contactConverter;
    private CompanyRepository companyRepository;
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
        return individual;
    }


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setAddressConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Autowired
    public void setIndividualContactRepository(IndividualContactRepository individualContactRepository) {
        this.individualContactRepository = individualContactRepository;
    }
}
