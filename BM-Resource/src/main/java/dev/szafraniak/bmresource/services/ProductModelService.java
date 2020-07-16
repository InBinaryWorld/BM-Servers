package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.ProductModelConverter;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.entity.ProductModel;
import dev.szafraniak.bmresource.repository.ProductModelRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductModelService {

    private ProductModelRepository productModelRepository;
    private ProductModelConverter productModelConverter;


    public BmCollection<ProductModelGetDTO> getModels(Long companyId) {
        return productModelRepository
                .findAllByCompany_Id(companyId).stream()
                .map(productModelConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public ProductModelGetDTO getModel(Long productModelId) {
        ProductModel productModel = productModelRepository.findById(productModelId).get();
        return productModelConverter.convertToDTO(productModel);
    }

    public ProductModelGetDTO createProductModel(ProductModelPostDTO dto, Long companyId) {
        ProductModel productModel = productModelConverter.convertFromDTO(dto, companyId);
        ProductModel saved = productModelRepository.save(productModel);
        return productModelConverter.convertToDTO(saved);
    }

    public ProductModelGetDTO updateProductModel(ProductModelPutDTO dto, Long productModelId) {
        ProductModel model = productModelConverter.convertFromDTO(dto, productModelId);
        ProductModel saved = productModelRepository.save(model);
        return productModelConverter.convertToDTO(saved);
    }

    public boolean deleteProductModel(Long productModelId) {
        productModelRepository.deleteById(productModelId);
        return true;
    }

    @Autowired
    public void setProductModelRepository(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
    }

    @Autowired
    public void setProductModelConverter(ProductModelConverter productModelConverter) {
        this.productModelConverter = productModelConverter;
    }

}
