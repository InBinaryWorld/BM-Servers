package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.entity.price.PricePutDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.Price;
import dev.szafraniak.bmresource.model.entity.ProductGroup;
import dev.szafraniak.bmresource.model.entity.ProductModel;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductModelConverter implements ConverterCompanyInterface<ProductModel, ProductModelGetDTO, ProductModelPostDTO, ProductModelPutDTO> {

    private PriceConverter priceConverter;
    private CompanyRepository companyRepository;
    private ProductGroupConverter groupConverter;
    private ProductModelRepository productModelRepository;


    public ProductModelGetDTO convertToDTO(ProductModel productModel) {
        if (productModel == null) {
            return null;
        }
        ProductModelGetDTO productDto = new ProductModelGetDTO();
        Price price = productModel.getPriceSuggestion();
        PriceGetDTO priceGetDTO = priceConverter.convertToDTO(price);
        BaseGetDTO groupDto = groupConverter.convertToBaseDTO(productModel.getProductGroup());

        productDto.setId(productModel.getId());
        productDto.setName(productModel.getName());
        productDto.setBareCode(productModel.getBareCode());
        productDto.setProductGroup(groupDto);
        productDto.setQuantityUnit(productModel.getQuantityUnit());
        productDto.setPriceSuggestion(priceGetDTO);
        return productDto;
    }

    public ProductModel convertFromDTO(ProductModelPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Company company = companyRepository.findById(companyId).get();
        ProductModel productModel = new ProductModel();
        Price price = priceConverter.convertFromDTO(dto.getPriceSuggestion());
        ProductGroup group = groupConverter.retrieveFromId(dto.getProductGroupId());
        productModel.setName(dto.getName());
        productModel.setCompany(company);
        productModel.setBareCode(dto.getBareCode());
        productModel.setProducts(new ArrayList<>());
        productModel.setProductGroup(group);
        productModel.setQuantityUnit(dto.getQuantityUnit());
        productModel.setPriceSuggestion(price);
        return productModel;
    }

    public ProductModel convertFromDTO(ProductModelPutDTO dto, Long productModelId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        ProductModel productModel = productModelRepository.findById(productModelId).get();
        PricePutDTO priceDto = dto.getPriceSuggestion();
        Long priceId = productModel.getPriceSuggestion().getId();
        Price price = priceConverter.convertFromDTO(priceDto, priceId);
        ProductGroup group = groupConverter.retrieveFromId(dto.getProductGroupId());
        productModel.setName(dto.getName());
        productModel.setBareCode(dto.getBareCode());
        productModel.setQuantityUnit(dto.getQuantityUnit());
        productModel.setPriceSuggestion(price);
        productModel.setProductGroup(group);
        return productModel;
    }

    @Autowired
    public void setPriceConverter(PriceConverter priceConverter) {
        this.priceConverter = priceConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setGroupConverter(ProductGroupConverter groupConverter) {
        this.groupConverter = groupConverter;
    }

    @Autowired
    public void setProductModelRepository(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
    }
}
