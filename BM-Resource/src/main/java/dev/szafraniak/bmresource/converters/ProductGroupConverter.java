package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.converters.interfaces.ConverterCompanyInterface;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.shared.BaseGetDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.ProductGroup;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGroupConverter implements ConverterCompanyInterface<ProductGroup, ProductGroupGetDTO, ProductGroupPostDTO, ProductGroupPutDTO> {

    private CompanyRepository companyRepository;
    private ProductModelConverter productModelConverter;
    private ProductGroupRepository productGroupRepository;


    public ProductGroupGetDTO convertToDTO(ProductGroup productGroup) {
        List<ProductModelGetDTO> productModels = productGroup.getProductModels()
                .stream().map(productModelConverter::convertToDTO)
                .collect(Collectors.toList());
        ProductGroupGetDTO dto = new ProductGroupGetDTO();
        dto.setId(productGroup.getId());
        dto.setName(productGroup.getName());
        dto.setProductModels(productModels);
        return dto;
    }

    public BaseGetDTO convertToBaseDTO(ProductGroup group) {
        if (group == null) {
            return null;
        }
        BaseGetDTO baseGetDTO = new BaseGetDTO();
        baseGetDTO.setName(group.getName());
        baseGetDTO.setId(group.getId());
        return baseGetDTO;
    }

    public ProductGroup convertFromDTO(ProductGroupPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyRepository.findById(companyId).get();
        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(dto.getName());
        productGroup.setCompany(company);
        productGroup.setProductModels(new ArrayList<>());
        return productGroup;
    }

    public ProductGroup convertFromDTO(ProductGroupPutDTO dto, Long productGroupId) {
        if (dto == null) {
            return null;
        }
        ProductGroup productGroup = productGroupRepository.findById(productGroupId).get();
        productGroup.setName(dto.getName());
        return productGroup;
    }

    public ProductGroup retrieveFromId(Long productGroupId) {
        if (productGroupId == null) {
            return null;
        }
        return productGroupRepository.findById(productGroupId).get();
    }

    @Autowired
    public void setProductModelConverter(ProductModelConverter productModelConverter) {
        this.productModelConverter = productModelConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setProductGroupRepository(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

}
