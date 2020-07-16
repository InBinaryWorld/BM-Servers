package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.company.CompanyGetDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPostDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPutDTO;
import dev.szafraniak.bmresource.entity.Address;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.User;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter {

    private ModelMapper modelMapper;
    private UserService userService;
    private AddressConverter addressConverter;
    private CompanyRepository companyRepository;


    public CompanyGetDTO convertToDTO(Company company) {
        return modelMapper.map(company, CompanyGetDTO.class);
    }

    public Company convertFromDTO(CompanyPostDTO dto) {
        Company company = modelMapper.map(dto, Company.class);
        Address address = addressConverter.convertFromDTO(dto.getHeadquarter());
        User user = userService.getOrCreateContextUser();
        company.setHeadquarter(address);
        company.setOwner(user);
        return company;
    }


    public Company convertFromDTO(CompanyPutDTO dto, Long companyId) {
        Company original = companyRepository.findById(companyId).get();
        Long headquarterId = original.getHeadquarter().getId();
        Address address = addressConverter.convertFromDTO(dto.getHeadquarter(), headquarterId);
        original.setTaxIdentityNumber(dto.getTaxIdentityNumber());
        original.setInvoicePrefix(dto.getInvoicePrefix());
        original.setHeadquarter(address);
        original.setName(dto.getName());
        return original;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAddressConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
