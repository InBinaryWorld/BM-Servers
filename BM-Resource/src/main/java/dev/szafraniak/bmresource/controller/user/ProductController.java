package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.product.ProductGetDTO;
import dev.szafraniak.bmresource.dto.product.ProductPostDTO;
import dev.szafraniak.bmresource.dto.product.ProductPutDTO;
import dev.szafraniak.bmresource.services.entity.ProductService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/products")
public class ProductController {

    private ProductService service;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<ProductGetDTO> getAll(@PathVariable Long companyId) {
        return service.getAll(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkProductForCreate(#dto, #companyId)")
    public ProductGetDTO create(@PathVariable Long companyId,
                                @Valid @RequestBody ProductPostDTO dto) {
        return service.create(dto, companyId);
    }

    @GetMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProduct(#companyId, #entityId)")
    public ProductGetDTO getEntity(@PathVariable Long companyId,
                                   @PathVariable Long entityId) {
        return service.getEntity(entityId);
    }

    @PutMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProduct(#companyId, #entityId)")
    public ProductGetDTO update(@PathVariable Long companyId,
                                @PathVariable Long entityId,
                                @Valid @RequestBody ProductPutDTO dto) {
        return service.update(dto, entityId);
    }

    @DeleteMapping("/{entityId}")
    @PreAuthorize("@permissionChecker.checkProduct(#companyId, #entityId)")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long entityId,
                                              @PathVariable String companyId) {
        boolean success = service.delete(entityId);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.CONFLICT;
        return new ResponseEntity<>(status);
    }

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }
}

