package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.ProductGroupConverter;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.entity.ProductGroup;
import dev.szafraniak.bmresource.repository.ProductGroupRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupService {

    private ProductGroupConverter productGroupConverter;
    private ProductGroupRepository productGroupRepository;


    public BmCollection<ProductGroupGetDTO> getGroups(Long companyId) {
        return productGroupRepository
                .findAllByCompany_Id(companyId).stream()
                .map(productGroupConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public ProductGroupGetDTO getGroup(Long productGroupId) {
        ProductGroup productGroup = productGroupRepository.findById(productGroupId).get();
        return productGroupConverter.convertToDTO(productGroup);
    }

    public ProductGroupGetDTO createProductGroup(ProductGroupPostDTO dto, Long companyId) {
        ProductGroup productGroup = productGroupConverter.convertFromDTO(dto, companyId);
        ProductGroup saved = productGroupRepository.save(productGroup);
        return productGroupConverter.convertToDTO(saved);
    }

    public ProductGroupGetDTO updateProductGroup(ProductGroupPutDTO dto, Long productGroupId) {
        ProductGroup productGroup = productGroupConverter.convertFromDTO(dto, productGroupId);
        ProductGroup saved = productGroupRepository.save(productGroup);
        return productGroupConverter.convertToDTO(saved);
    }

    public boolean deleteProductGroup(Long productGroupId) {
        productGroupRepository.deleteById(productGroupId);
        return true;
    }

    @Autowired
    public void setProductGroupConverter(ProductGroupConverter productGroupConverter) {
        this.productGroupConverter = productGroupConverter;
    }

    @Autowired
    public void setProductGroupRepository(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

}
