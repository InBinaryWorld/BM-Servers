package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.companyContact.CompanyContactGetDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPostDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.CompanyContact;
import dev.szafraniak.bmresource.repository.CompanyContactRepository;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyContactConverter {

    private ModelMapper modelMapper;
    private AddressConverter addressConverter;
    private CompanyRepository companyRepository;
    private CompanyContactRepository companyContactRepository;


    public CompanyContactGetDTO convertToDTO(CompanyContact companyContact) {
        return modelMapper.map(companyContact, CompanyContactGetDTO.class);
    }

    public CompanyContact convertFromDTO(CompanyContactPostDTO dto, Long companyId) {
        CompanyContact companyContact = modelMapper.map(dto, CompanyContact.class);
        Company company = companyRepository.findById(companyId).get();
        companyContact.setCompany(company);
        return companyContact;
    }

    public CompanyContact convertFromDTO(CompanyContactPutDTO dto, Long companyContactId) {
        CompanyContact companyContact = companyContactRepository.findById(companyContactId).get();
        Long addressId = companyContact.getAddress().getId();
        Address address = addressConverter.convertFromDTO(dto.getAddress(), addressId);
        companyContact.setTaxIdentityNumber(dto.getTaxIdentityNumber());
        companyContact.setAddress(address);
        companyContact.setName(dto.getName());
        companyContact.setPhone(dto.getPhone());
        return companyContact;
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
    public void setCompanyContactRepository(CompanyContactRepository companyContactRepository) {
        this.companyContactRepository = companyContactRepository;
    }
}
