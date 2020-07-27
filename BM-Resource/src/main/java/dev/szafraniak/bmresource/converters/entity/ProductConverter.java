package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.product.ProductGetDTO;
import dev.szafraniak.bmresource.dto.entity.product.ProductPostDTO;
import dev.szafraniak.bmresource.dto.entity.product.ProductPutDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.Product;
import dev.szafraniak.bmresource.model.entity.ProductModel;
import dev.szafraniak.bmresource.model.entity.Warehouse;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.ProductModelRepository;
import dev.szafraniak.bmresource.repository.entity.ProductRepository;
import dev.szafraniak.bmresource.repository.entity.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements ConverterCompanyInterface<Product, ProductGetDTO, ProductPostDTO, ProductPutDTO> {

    private WarehouseConverter warehouseConverter;
    private ProductModelConverter productModelConverter;

    private CompanyRepository companyRepository;
    private ProductRepository productRepository;
    private WarehouseRepository warehouseRepository;
    private ProductModelRepository productModelRepository;

    @Override
    public ProductGetDTO convertToDTO(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductModelGetDTO productModel = productModelConverter.convertToDTO(entity.getProductModel());
        BaseGetDTO warehouse = warehouseConverter.convertToBaseDTO(entity.getWarehouse());
        ProductGetDTO dto = new ProductGetDTO();
        dto.setQuantity(entity.getQuantity());
        dto.setProductModel(productModel);
        dto.setWarehouse(warehouse);
        return dto;
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Product convertFromDTO(ProductPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyRepository.findById(companyId).get();
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId()).get();
        ProductModel productModel = productModelRepository.findById(dto.getProductModelId()).get();
        Product product = new Product();
        product.setCompany(company);
        product.setWarehouse(warehouse);
        product.setProductModel(productModel);
        product.setQuantity(dto.getQuantity());
        return product;
    }

    @Override
    public Product convertFromDTO(ProductPutDTO dto, Long entityId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Product product = productRepository.findById(entityId).get();
        product.setQuantity(dto.getQuantity());
        return product;
    }

    @Autowired
    public void setWarehouseConverter(WarehouseConverter warehouseConverter) {
        this.warehouseConverter = warehouseConverter;
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
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Autowired
    public void setProductModelRepository(ProductModelRepository productModelRepository) {
        this.productModelRepository = productModelRepository;
    }
}
