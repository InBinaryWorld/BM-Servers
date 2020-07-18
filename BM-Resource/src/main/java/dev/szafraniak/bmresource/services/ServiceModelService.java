package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.ServiceModelConverter;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.entity.ServiceModel;
import dev.szafraniak.bmresource.repository.ServiceModelRepository;
import dev.szafraniak.bmresource.utils.BmCollection;
import dev.szafraniak.bmresource.utils.BmCollectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceModelService {

    private ServiceModelRepository serviceModelRepository;
    private ServiceModelConverter serviceModelConverter;


    public BmCollection<ServiceModelGetDTO> getModels(Long companyId) {
        return serviceModelRepository
                .findAllByCompany_Id(companyId).stream()
                .map(serviceModelConverter::convertToDTO)
                .collect(BmCollectors.toCollection());
    }

    public ServiceModelGetDTO getModel(Long serviceModuleId) {
        ServiceModel serviceModel = serviceModelRepository.findById(serviceModuleId).get();
        return serviceModelConverter.convertToDTO(serviceModel);
    }

    public ServiceModelGetDTO createProductModel(ServiceModelPostDTO dto, Long companyId) {
        ServiceModel serviceModel = serviceModelConverter.convertFromDTO(dto, companyId);
        ServiceModel saved = serviceModelRepository.save(serviceModel);
        return serviceModelConverter.convertToDTO(saved);
    }

    public ServiceModelGetDTO updateProductModel(ServiceModelPutDTO dto, Long serviceModelId) {
        ServiceModel model = serviceModelConverter.convertFromDTO(dto, serviceModelId);
        ServiceModel saved = serviceModelRepository.save(model);
        return serviceModelConverter.convertToDTO(saved);
    }

    public boolean deleteServiceModel(Long productModelId) {
        serviceModelRepository.deleteById(productModelId);
        return true;
    }

    @Autowired
    public void setServiceModelRepository(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }

    @Autowired
    public void setServiceModelConverter(ServiceModelConverter serviceModelConverter) {
        this.serviceModelConverter = serviceModelConverter;
    }
}
