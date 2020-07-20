package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.company.CompanyGetDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPostDTO;
import dev.szafraniak.bmresource.dto.company.CompanyPutDTO;
import dev.szafraniak.bmresource.services.entity.CompanyService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies")
public class CompanyController {

    private CompanyService companyService;

    @GetMapping()
    public BmCollection<CompanyGetDTO> getCompanies() {
        return companyService.getAll();
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public CompanyGetDTO getCompanies(@PathVariable Long companyId) {
        return companyService.getEntity(companyId);
    }

    @PostMapping
    public CompanyGetDTO createCompany(@Valid @RequestBody CompanyPostDTO dto) {
        return companyService.create(dto);
    }

    @PutMapping("/{companyId}")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public CompanyGetDTO updateCompany(@Valid @RequestBody CompanyPutDTO dto,
                                       @PathVariable Long companyId) {
        return companyService.update(dto, companyId);
    }

    @DeleteMapping("/{companyId}")
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        boolean success = companyService.delete(companyId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}

