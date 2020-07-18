package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.entity.Company;
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
    private CompanyRepository companyRepository;
    private IndividualContactRepository individualContactRepository;


    public IndividualContactGetDTO convertToDTO(IndividualContact individualContact) {
        return modelMapper.map(individualContact, IndividualContactGetDTO.class);
    }

    public IndividualContact convertFromDTO(IndividualContactPostDTO dto, Long companyId) {
        IndividualContact individual = modelMapper.map(dto, IndividualContact.class);
        Company company = companyRepository.findById(companyId).get();
        individual.setCompany(company);
        return individual;
    }

    public IndividualContact convertFromDTO(IndividualContactPutDTO dto, Long individualId) {
        IndividualContact individual = individualContactRepository.findById(individualId).get();
        Long addressId = individual.getAddress().getId();
        Address address = addressConverter.convertFromDTO(dto.getAddress(), addressId);
        individual.setFirstName(dto.getFirstName());
        individual.setLastName(dto.getLastName());
        individual.setAddress(address);
        individual.setPhone(dto.getPhone());
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
