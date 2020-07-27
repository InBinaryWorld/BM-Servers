package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.ProductGroupConverter;
import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.entity.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.model.entity.ProductGroup;
import dev.szafraniak.bmresource.repository.entity.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupService extends AbstractCompanyService<ProductGroup, ProductGroupRepository,
        ProductGroupConverter, ProductGroupGetDTO, ProductGroupPostDTO, ProductGroupPutDTO> {

    @Autowired
    public ProductGroupService(ProductGroupConverter converter, ProductGroupRepository repository) {
        super(converter, repository);
    }

}
