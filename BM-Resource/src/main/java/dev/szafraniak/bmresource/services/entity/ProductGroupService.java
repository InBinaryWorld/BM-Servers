package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.ProductGroupConverter;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupGetDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPostDTO;
import dev.szafraniak.bmresource.dto.productGroup.ProductGroupPutDTO;
import dev.szafraniak.bmresource.entity.ProductGroup;
import dev.szafraniak.bmresource.repository.entity.ProductGroupRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
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
