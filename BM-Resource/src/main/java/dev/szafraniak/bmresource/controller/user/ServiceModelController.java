package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.services.ServiceModelService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/services/models")
public class ServiceModelController {

    private ServiceModelService serviceModelService;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<ServiceModelGetDTO> getModels(@PathVariable Long companyId) {
        return serviceModelService.getModels(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public ServiceModelGetDTO createServiceModel(@PathVariable Long companyId,
                                                 @Valid @RequestBody ServiceModelPostDTO dto) {
        return serviceModelService.createProductModel(dto, companyId);
    }

    @GetMapping("/{serviceModelId}")
    @PreAuthorize("@permissionChecker.checkServiceModel(#companyId, #serviceModelId)")
    public ServiceModelGetDTO getModel(@PathVariable Long companyId,
                                       @PathVariable Long serviceModelId) {
        return serviceModelService.getModel(serviceModelId);
    }

    @PutMapping("/{serviceModelId}")
    @PreAuthorize("@permissionChecker.checkServiceModel(#companyId, #serviceModelId)")
    public ServiceModelGetDTO updateProductModel(@PathVariable Long companyId,
                                                 @PathVariable Long serviceModelId,
                                                 @Valid @RequestBody ServiceModelPutDTO dto) {
        return serviceModelService.updateProductModel(dto, serviceModelId);
    }

    @DeleteMapping("/{serviceModelId}")
    @PreAuthorize("@permissionChecker.checkServiceModel(#companyId, #serviceModelId)")
    public void deleteCompany(@PathVariable Long serviceModelId, @PathVariable String companyId) {
        serviceModelService.deleteServiceModel(serviceModelId);
    }

    @Autowired
    public void setServiceModelService(ServiceModelService serviceModelService) {
        this.serviceModelService = serviceModelService;
    }
}

