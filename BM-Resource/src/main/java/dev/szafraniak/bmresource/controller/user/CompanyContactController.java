package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.companyContact.CompanyContactGetDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPostDTO;
import dev.szafraniak.bmresource.dto.companyContact.CompanyContactPutDTO;
import dev.szafraniak.bmresource.services.CompanyContactService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/contacts/company")
public class CompanyContactController {

    private CompanyContactService companyContactService;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<CompanyContactGetDTO> getAll(@PathVariable Long companyId) {
        return companyContactService.getContacts(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public CompanyContactGetDTO create(@PathVariable Long companyId,
                                       @Valid @RequestBody CompanyContactPostDTO dto) {
        return companyContactService.createContact(dto, companyId);
    }

    @GetMapping("/{contactId}")
    @PreAuthorize("@permissionChecker.checkCompanyContact(#companyId, #contactId)")
    public CompanyContactGetDTO get(@PathVariable Long companyId,
                                    @PathVariable Long contactId) {
        return companyContactService.getContact(contactId);
    }

    @PutMapping("/{contactId}")
    @PreAuthorize("@permissionChecker.checkCompanyContact(#companyId, #contactId)")
    public CompanyContactGetDTO update(@PathVariable Long companyId,
                                       @PathVariable Long contactId,
                                       @Valid @RequestBody CompanyContactPutDTO dto) {
        return companyContactService.updateContact(dto, contactId);
    }

    @DeleteMapping("/{contactId}")
    @PreAuthorize("@permissionChecker.checkCompanyContact(#companyId, #contactId)")
    public void delete(@PathVariable Long contactId, @PathVariable String companyId) {
        companyContactService.deleteContact(contactId);
    }

    @Autowired
    public void setCompanyContactService(CompanyContactService companyContactService) {
        this.companyContactService = companyContactService;
    }
}

