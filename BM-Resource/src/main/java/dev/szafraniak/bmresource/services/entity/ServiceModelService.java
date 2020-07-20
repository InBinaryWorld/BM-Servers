package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.ServiceModelConverter;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.entity.ServiceModel;
import dev.szafraniak.bmresource.repository.entity.ServiceModelRepository;
import dev.szafraniak.bmresource.services.AbstractCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceModelService extends AbstractCompanyService<ServiceModel, ServiceModelRepository,
        ServiceModelConverter, ServiceModelGetDTO, ServiceModelPostDTO, ServiceModelPutDTO> {

    @Autowired
    public ServiceModelService(ServiceModelConverter converter, ServiceModelRepository repository) {
        super(converter, repository);
    }
}
