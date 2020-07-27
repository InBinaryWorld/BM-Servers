package dev.szafraniak.bmresource.controller.entity;

import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.services.entity.ProductModelService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/products/models")
public class ProductModelController {

    private ProductModelService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<ProductModelGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAllDTO(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkForCreate(#dto, #companyId)")
    public ProductModelGetDTO create(@PathVariable Long companyId,
                                     @Valid @RequestBody ProductModelPostDTO dto) {
        return service.createFromDTO(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductModel(#companyId, #entityId)")
    public ProductModelGetDTO getEntity(@PathVariable Long companyId,
                                        @PathVariable Long entityId) {
        return service.getEntityDTO(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkForUpdate(#dto, #companyId, #entityId)")
    public ProductModelGetDTO update(@PathVariable Long companyId,
                                     @PathVariable Long entityId,
                                     @Valid @RequestBody ProductModelPutDTO dto) {
        return service.updateFromDTO(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProductModel(#companyId, #entityId)")
    public ResponseEntity<Void> delete(@PathVariable Long entityId,
                                       @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(ProductModelService service) {
        this.service = service;
    }
}

