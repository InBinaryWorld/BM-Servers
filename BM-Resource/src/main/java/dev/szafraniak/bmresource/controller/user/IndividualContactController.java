package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.individualContact.IndividualContactGetDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPostDTO;
import dev.szafraniak.bmresource.dto.individualContact.IndividualContactPutDTO;
import dev.szafraniak.bmresource.services.entity.IndividualContactService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/contacts/individual")
public class IndividualContactController {

    private IndividualContactService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<IndividualContactGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public IndividualContactGetDTO create(@PathVariable Long companyId,
                                          @Valid @RequestBody IndividualContactPostDTO dto) {
        return service.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkIndividualContact(#companyId, #entityId)")
    public IndividualContactGetDTO getEntity(@PathVariable Long companyId,
                                             @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkIndividualContact(#companyId, #entityId)")
    public IndividualContactGetDTO update(@PathVariable Long companyId,
                                          @PathVariable Long entityId,
                                          @Valid @RequestBody IndividualContactPutDTO dto) {
        return service.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkIndividualContact(#companyId, #entityId)")
    public ResponseEntity<Void> delete(@PathVariable Long entityId, @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(IndividualContactService service) {
        this.service = service;
    }
}

