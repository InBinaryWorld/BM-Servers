package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.ProductGroup;
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
        @SuppressWarnings("OptionalGetWithoutIsPresent")
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
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        ProductGroup productGroup = productGroupRepository.findById(productGroupId).get();
        productGroup.setName(dto.getName());
        return productGroup;
    }

    public ProductGroup retrieveFromId(Long productGroupId) {
        if (productGroupId == null) {
            return null;
        }
        //noinspection OptionalGetWithoutIsPresent
        return productGroupRepository.findById(productGroupId).get();
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setProductModelConverter(ProductModelConverter productModelConverter) {
        this.productModelConverter = productModelConverter;
    }

    @Autowired
    public void setProductGroupRepository(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }
}
