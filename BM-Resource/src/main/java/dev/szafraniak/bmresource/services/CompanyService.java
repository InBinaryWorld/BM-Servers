package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.CompanyConverter;
import dev.szafraniak.bmresource.dto.company.CompanyGetDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPostDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPutDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;
    private CompanyConverter companyConverter;
    private UserService userService;

    public BmCollection<CompanyGetDTO> getCompanies() {
        return userService
                .getOrCreateContextUser()
                .getCompanies().stream()
                .map(companyConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public CompanyGetDTO createCompany(CompanyPostDTO dto) {
        Company company = companyConverter.convertFromDTO(dto);
        Company saved = companyRepository.save(company);
        return companyConverter.convertToDTO(saved);
    }

    public CompanyGetDTO updateCompany(CompanyPutDTO dto, Long companyId) {
        Company company = companyConverter.convertFromDTO(dto, companyId);
        Company saved = companyRepository.save(company);
        return companyConverter.convertToDTO(saved);
    }

    public CompanyGetDTO getCompany(Long companyId) {
        Company saved = companyRepository.findById(companyId).get();
        return companyConverter.convertToDTO(saved);
    }

    public boolean deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
        return true;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setCompanyConverter(CompanyConverter companyConverter) {
        this.companyConverter = companyConverter;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
