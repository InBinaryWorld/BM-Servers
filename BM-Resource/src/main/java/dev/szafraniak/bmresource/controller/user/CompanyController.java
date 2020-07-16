package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.company.CompanyGetDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPostDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPutDTO;
import dev.szafraniak.bmresource.services.CompanyService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    private CompanyService companyService;

    @GetMapping()
    public BmCollection<CompanyGetDTO> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public CompanyGetDTO getCompanies(@PathVariable Long companyId) {
        return companyService.getCompany(companyId);
    }

    @PostMapping
    public CompanyGetDTO createCompany(@Valid @RequestBody CompanyPostDTO dto) {
        return companyService.createCompany(dto);
    }

    @PutMapping("/{companyId}")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public CompanyGetDTO updateCompany(@Valid @RequestBody CompanyPutDTO dto,
                                       @PathVariable Long companyId) {
        return companyService.updateCompany(dto, companyId);
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public void deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}

