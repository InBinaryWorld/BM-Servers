package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.services.ProductModelService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/products/models")
public class ProductModelController {

    private ProductModelService productModelService;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<ProductModelGetDTO> getModels(@PathVariable Long companyId) {
        return productModelService.getModels(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkForCreate(#dto, #companyId)")
    public ProductModelGetDTO createProductModel(@PathVariable Long companyId,
                                                 @Valid @RequestBody ProductModelPostDTO dto) {
        return productModelService.createProductModel(dto, companyId);
    }

    @GetMapping("/{productModelId}")
    @PreAuthorize("@permissionChecker.checkProductModel(#companyId, #productModelId)")
    public ProductModelGetDTO getModel(@PathVariable Long companyId,
                                       @PathVariable Long productModelId) {
        return productModelService.getModel(productModelId);
    }

    @PutMapping("/{productModelId}")
    @PreAuthorize("@permissionChecker.checkForUpdate(#dto, #companyId, #productModelId)")
    public ProductModelGetDTO updateProductModel(@PathVariable Long companyId,
                                                 @PathVariable Long productModelId,
                                                 @Valid @RequestBody ProductModelPutDTO dto) {
        return productModelService.updateProductModel(dto, productModelId);
    }

    @DeleteMapping("/{productModelId}")
    @PreAuthorize("@permissionChecker.checkProductModel(#companyId, #productModelId)")
    public void deleteCompany(@PathVariable Long productModelId, @PathVariable String companyId) {
        productModelService.deleteProductModel(productModelId);
    }

    @Autowired
    public void setProductModelService(ProductModelService productModelService) {
        this.productModelService = productModelService;
    }
}

