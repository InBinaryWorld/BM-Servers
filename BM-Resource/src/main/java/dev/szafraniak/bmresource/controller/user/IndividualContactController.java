package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.services.IndividualContactService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/contacts/individual")
public class IndividualContactController {

    private IndividualContactService individualContactService;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<IndividualContactGetDTO> getAll(@PathVariable Long companyId) {
        return individualContactService.getContacts(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public IndividualContactGetDTO create(@PathVariable Long companyId,
                                          @Valid @RequestBody IndividualContactPostDTO dto) {
        return individualContactService.createContact(dto, companyId);
    }

    @GetMapping("/{contactId}")
    @PreAuthorize("@permissionChecker.checkIndividualContact(#companyId, #contactId)")
    public IndividualContactGetDTO get(@PathVariable Long companyId,
                                       @PathVariable Long contactId) {
        return individualContactService.getContact(contactId);
    }

    @PutMapping("/{contactId}")
    @PreAuthorize("@permissionChecker.checkIndividualContact(#companyId, #contactId)")
    public IndividualContactGetDTO update(@PathVariable Long companyId,
                                          @PathVariable Long contactId,
                                          @Valid @RequestBody IndividualContactPutDTO dto) {
        return individualContactService.updateContact(dto, contactId);
    }

    @DeleteMapping("/{contactId}")
    @PreAuthorize("@permissionChecker.checkIndividualContact(#companyId, #contactId)")
    public void delete(@PathVariable Long contactId, @PathVariable String companyId) {
        individualContactService.deleteContact(contactId);
    }

    @Autowired
    public void setIndividualContactService(IndividualContactService individualContactService) {
        this.individualContactService = individualContactService;
    }
}

