package dev.szafraniak.bmresource.controller.entity;

import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.services.entity.ProductGroupService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/products/groups")
public class ProductGroupController {

    private ProductGroupService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<ProductGroupGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAllDTO(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public ProductGroupGetDTO create(@PathVariable Long companyId,
                                     @Valid @RequestBody ProductGroupPostDTO dto) {
        return service.createFromDTO(dto, companyId);
    }


    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #entityId)")
    public ProductGroupGetDTO getEntity(@PathVariable Long companyId,
                                        @PathVariable Long entityId) {
        return service.getEntityDTO(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #entityId)")
    public ProductGroupGetDTO update(@PathVariable Long companyId,
                                     @PathVariable Long entityId,
                                     @Valid @RequestBody ProductGroupPutDTO dto) {
        return service.updateFromDTO(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #entityId)")
    public ResponseEntity<Void> delete(@PathVariable String companyId, @PathVariable Long entityId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(ProductGroupService service) {
        this.service = service;
    }
}

