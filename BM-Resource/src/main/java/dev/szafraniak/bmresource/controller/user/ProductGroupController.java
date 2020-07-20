package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPutDTO;
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
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public ProductGroupGetDTO create(@PathVariable Long companyId,
                                     @Valid @RequestBody ProductGroupPostDTO dto) {
        return service.create(dto, companyId);
    }


    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #entityId)")
    public ProductGroupGetDTO getEntity(@PathVariable Long companyId,
                                        @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #entityId)")
    public ProductGroupGetDTO update(@PathVariable Long companyId,
                                     @PathVariable Long entityId,
                                     @Valid @RequestBody ProductGroupPutDTO dto) {
        return service.update(dto, entityId);
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

