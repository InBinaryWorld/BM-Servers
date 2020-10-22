package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.ProductConverter;
import dev.szafraniak.bmresource.dto.entity.product.ProductGetDTO;
import dev.szafraniak.bmresource.dto.entity.product.ProductPostDTO;
import dev.szafraniak.bmresource.dto.entity.product.ProductPutDTO;
import dev.szafraniak.bmresource.model.entity.Product;
import dev.szafraniak.bmresource.repository.entity.ProductRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProductService extends AbstractCompanyService<Product, ProductRepository,
        ProductConverter, ProductGetDTO, ProductPostDTO, ProductPutDTO> {

    @Autowired
    public ProductService(ProductConverter converter, ProductRepository repository) {
        super(converter, repository);
    }

    public BmCollection<ProductGetDTO> getAllDTO(Long companyId, Long warehouseId, Long productModelId) {
        Stream<ProductGetDTO> productStream = repository.findAllByCompany_Id(companyId)
                .stream().map(converter::convertToDTO);
        if (warehouseId != null) {
            productStream = productStream.filter(product -> product.getWarehouse().getId().equals(warehouseId));
        }
        if (productModelId != null) {
            productStream = productStream.filter(product -> product.getProductModel().getId().equals(productModelId));
        }
        return productStream.collect(BmCollectors.toCollection());
    }
}
