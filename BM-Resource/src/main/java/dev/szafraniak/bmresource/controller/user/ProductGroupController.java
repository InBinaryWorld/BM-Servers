package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.services.ProductGroupService;
import dev.szafraniak.bmresource.utils.BmCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/companies/{companyId}/products/groups")
public class ProductGroupController {

    private ProductGroupService productGroupService;

    @GetMapping()
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public BmCollection<ProductGroupGetDTO> getGroups(@PathVariable Long companyId) {
        return productGroupService.getGroups(companyId);
    }

    @PostMapping
    @PreAuthorize("@permissionChecker.checkCompanyId(#companyId)")
    public ProductGroupGetDTO createProductGroup(@PathVariable Long companyId,
                                                 @Valid @RequestBody ProductGroupPostDTO dto) {
        return productGroupService.createProductGroup(dto, companyId);
    }


    @GetMapping("/{productGroupId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #productGroupId)")
    public ProductGroupGetDTO getProductGroup(@PathVariable Long companyId,
                                              @PathVariable Long productGroupId) {
        return productGroupService.getGroup(productGroupId);
    }

    @PutMapping("/{productGroupId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #productGroupId)")
    public ProductGroupGetDTO updateProductGroup(@PathVariable Long companyId,
                                                 @PathVariable Long productGroupId,
                                                 @Valid @RequestBody ProductGroupPutDTO dto) {
        return productGroupService.updateProductGroup(dto, productGroupId);
    }

    @DeleteMapping("/{productGroupId}")
    @PreAuthorize("@permissionChecker.checkProductGroup(#companyId, #productGroupId)")
    public void deleteCompany(@PathVariable String companyId, @PathVariable Long productGroupId) {
        productGroupService.deleteProductGroup(productGroupId);
    }

    @Autowired
    public void setProductGroupService(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }
}

