package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.ProductGroup;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.repository.ProductGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductGroupConverter {

    private ModelMapper modelMapper;
    private CompanyRepository companyRepository;
    private ProductGroupRepository productGroupRepository;


    public ProductGroupGetDTO convertToDTO(ProductGroup productGroup) {
        return modelMapper.map(productGroup, ProductGroupGetDTO.class);
    }

    public ProductGroup convertFromDTO(ProductGroupPostDTO dto, Long companyId) {
        ProductGroup productGroup = modelMapper.map(dto, ProductGroup.class);
        Company company = companyRepository.findById(companyId).get();
        productGroup.setCompany(company);
        return productGroup;
    }

    public ProductGroup convertFromDTO(ProductGroupPutDTO dto, Long productGroupId) {
        ProductGroup productGroup = productGroupRepository.findById(productGroupId).get();
        productGroup.setName(dto.getName());
        return productGroup;
    }


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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
