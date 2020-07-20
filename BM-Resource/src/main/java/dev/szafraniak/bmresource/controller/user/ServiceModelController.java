package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.services.entity.ServiceModelService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/services/models")
public class ServiceModelController {

    private ServiceModelService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<ServiceModelGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public ServiceModelGetDTO create(@PathVariable Long companyId,
                                     @Valid @RequestBody ServiceModelPostDTO dto) {
        return service.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkServiceModel(#companyId, #entityId)")
    public ServiceModelGetDTO getEntity(@PathVariable Long companyId,
                                        @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkServiceModel(#companyId, #entityId)")
    public ServiceModelGetDTO update(@PathVariable Long companyId,
                                     @PathVariable Long entityId,
                                     @Valid @RequestBody ServiceModelPutDTO dto) {
        return service.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkServiceModel(#companyId, #entityId)")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long entityId,
                                              @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(ServiceModelService service) {
        this.service = service;
    }
}

