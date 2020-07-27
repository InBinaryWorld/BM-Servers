package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.ServiceModelConverter;
import dev.szafraniak.bmresource.dto.entity.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.entity.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.model.entity.ServiceModel;
import dev.szafraniak.bmresource.repository.entity.ServiceModelRepository;
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
