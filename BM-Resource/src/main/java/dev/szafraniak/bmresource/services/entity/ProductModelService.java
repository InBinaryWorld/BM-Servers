package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.ProductModelConverter;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPostDTO;
import dev.szafraniak.bmresource.dto.entity.productmodel.ProductModelPutDTO;
import dev.szafraniak.bmresource.model.entity.ProductModel;
import dev.szafraniak.bmresource.repository.entity.ProductModelRepository;
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
