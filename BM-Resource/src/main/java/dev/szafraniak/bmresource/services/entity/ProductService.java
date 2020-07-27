package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.ProductConverter;
import dev.szafraniak.bmresource.dto.entity.product.ProductGetDTO;
import dev.szafraniak.bmresource.dto.entity.product.ProductPostDTO;
import dev.szafraniak.bmresource.dto.entity.product.ProductPutDTO;
import dev.szafraniak.bmresource.model.entity.Product;
import dev.szafraniak.bmresource.repository.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractCompanyService<Product, ProductRepository,
        ProductConverter, ProductGetDTO, ProductPostDTO, ProductPutDTO> {

    @Autowired
    public ProductService(ProductConverter converter, ProductRepository repository) {
        super(converter, repository);
    }
}
