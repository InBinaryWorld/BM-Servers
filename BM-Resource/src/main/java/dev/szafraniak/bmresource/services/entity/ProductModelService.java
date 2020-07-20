package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.ProductModelConverter;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.entity.ProductModel;
import dev.szafraniak.bmresource.repository.entity.ProductModelRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductModelService extends AbstractCompanyService<ProductModel, ProductModelRepository,
        ProductModelConverter, ProductModelGetDTO, ProductModelPostDTO, ProductModelPutDTO> {

    @Autowired
    public ProductModelService(ProductModelConverter converter, ProductModelRepository repository) {
        super(converter, repository);
    }

}
