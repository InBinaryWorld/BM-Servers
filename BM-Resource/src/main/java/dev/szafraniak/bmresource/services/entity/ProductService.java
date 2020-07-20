package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.ProductConverter;
import dev.szafraniak.bmresource.dto.product.ProductGetDTO;
import dev.szafraniak.bmresource.dto.product.ProductPostDTO;
import dev.szafraniak.bmresource.dto.product.ProductPutDTO;
import dev.szafraniak.bmresource.entity.Product;
import dev.szafraniak.bmresource.repository.entity.ProductRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
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
