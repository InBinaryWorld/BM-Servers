package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.companyContact.CompanyContactGetDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPostDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPutDTO;
import dev.szafraniak.bmresource.services.entity.CompanyContactService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/contacts/company")
public class CompanyContactController {

    private CompanyContactService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<CompanyContactGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public CompanyContactGetDTO create(@PathVariable Long companyId,
                                       @Valid @RequestBody CompanyContactPostDTO dto) {
        return service.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkCompanyContact(#companyId, #entityId)")
    public CompanyContactGetDTO get(@PathVariable Long companyId,
                                    @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkCompanyContact(#companyId, #entityId)")
    public CompanyContactGetDTO update(@PathVariable Long companyId,
                                       @PathVariable Long entityId,
                                       @Valid @RequestBody CompanyContactPutDTO dto) {
        return service.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkCompanyContact(#companyId, #entityId)")
    public ResponseEntity<Void> delete(@PathVariable Long entityId, @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(CompanyContactService service) {
        this.service = service;
    }
}

