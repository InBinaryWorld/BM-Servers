package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.price.PricePutDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.dto.shared.BasePostDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.Price;
import dev.szafraniak.bmresource.entity.ProductGroup;
import dev.szafraniak.bmresource.entity.ProductModel;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.repository.ProductGroupRepository;
import dev.szafraniak.bmresource.repository.ProductModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductModelConverter {

    private ModelMapper modelMapper;
    private PriceConverter priceConverter;
    private CompanyRepository companyRepository;
    private ProductGroupRepository groupRepository;
    private ProductModelRepository productModelRepository;


    public ProductModelGetDTO convertToDTO(ProductModel productModel) {
        return modelMapper.map(productModel, ProductModelGetDTO.class);
    }

    public ProductModel convertFromDTO(ProductModelPostDTO dto, Long companyId) {
        ProductModel productModel = modelMapper.map(dto, ProductModel.class);
        Company company = companyRepository.findById(companyId).get();
        Price price = priceConverter.convertFromDTO(dto.getPriceSuggestion());
        productModel.setCompany(company);
        productModel.setPriceSuggestion(price);
        setGroup(productModel, dto.getProductGroup());
        return productModel;
    }

    public ProductModel convertFromDTO(ProductModelPutDTO dto, Long productModelId) {
        ProductModel productModel = productModelRepository.findById(productModelId).get();
        PricePutDTO priceDto = dto.getPriceSuggestion();
        Long priceId = productModel.getPriceSuggestion().getId();
        Price price = priceConverter.convertFromDTO(priceDto, priceId);
        productModel.setName(dto.getName());
        productModel.setBareCode(dto.getBareCode());
        productModel.setQuantityUnitId(dto.getQuantityUnitId());
        productModel.setPriceSuggestion(price);
        setGroup(productModel, dto.getProductGroup());
        return productModel;
    }

    private void setGroup(ProductModel productModel, BasePostDTO groupDto) {
        productModel.setProductGroup(null);
        if (groupDto != null) {
            ProductGroup group = groupRepository.findById(groupDto.getId()).get();
            productModel.setProductGroup(group);
        }
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
    public void setPriceConverter(PriceConverter priceConverter) {
        this.priceConverter = priceConverter;
    }

    @Autowired
    public void setGroupRepository(ProductGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setProductModelRepository(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
    }
}
