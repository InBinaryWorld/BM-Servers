package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.entity.company.CompanyGetDTO;
import dev.szafraniak.bmresource.dto.entity.company.CompanyPostDTO;
import dev.szafraniak.bmresource.dto.entity.company.CompanyPutDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import dev.szafraniak.bmresource.model.entity.Address;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.User;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.services.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CompanyConverter implements ConverterInterface<Company, CompanyGetDTO, CompanyPostDTO, CompanyPutDTO> {

    private UserService userService;
    private AddressConverter addressConverter;
    private CompanyRepository companyRepository;


    public BaseGetDTO convertToBaseDTO(Company company) {
        if (company == null) {
            return null;
        }
        BaseGetDTO baseGetDTO = new BaseGetDTO();
        baseGetDTO.setId(company.getId());
        baseGetDTO.setName(company.getName());
        return baseGetDTO;
    }

    public CompanyGetDTO convertToDTO(Company company) {
        if (company == null) {
            return null;
        }
        AddressGetDTO addressGetDTO = addressConverter.convertToDTO(company.getHeadquarter());
        CompanyGetDTO companyGetDTO = new CompanyGetDTO();
        companyGetDTO.setId(company.getId());
        companyGetDTO.setName(company.getName());
        companyGetDTO.setCurrency(company.getCurrency());
        companyGetDTO.setHeadquarter(addressGetDTO);
        companyGetDTO.setInvoicePrefix(company.getInvoicePrefix());
        companyGetDTO.setTaxIdentityNumber(company.getTaxIdentityNumber());
        return companyGetDTO;
    }

    @Override
    public Company convertFromDTO(CompanyPostDTO dto) {
        if (dto == null) {
            return null;
        }
        Address address = addressConverter.convertFromDTO(dto.getHeadquarter());
        User user = userService.getOrCreateContextUser();
        Company company = new Company();
        company.setName(dto.getName());
        company.setCurrency(dto.getCurrency());
        company.setInvoicePrefix(dto.getInvoicePrefix());
        company.setTaxIdentityNumber(dto.getTaxIdentityNumber());
        company.setOwner(user);
        company.setWorkers(new ArrayList<>());
        company.setInvoices(new ArrayList<>());
        company.setWarehouses(new ArrayList<>());
        company.setHeadquarter(address);
        company.setServiceModels(new ArrayList<>());
        company.setProductModels(new ArrayList<>());
        company.setProductGroups(new ArrayList<>());
        company.setBankAccounts(new ArrayList<>());
        company.setCompanyContacts(new ArrayList<>());
        company.setFinancialHistory(new ArrayList<>());
        company.setIndividualContacts(new ArrayList<>());
        return company;
    }

    public Company convertFromDTO(CompanyPutDTO dto, Long companyId) {
        if (dto == null || companyId == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
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
