package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.entity.price.PricePutDTO;
import dev.szafraniak.bmresource.dto.entity.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.entity.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.entity.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.Price;
import dev.szafraniak.bmresource.model.entity.ServiceModel;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.ServiceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceModelConverter implements ConverterCompanyInterface<ServiceModel, ServiceModelGetDTO, ServiceModelPostDTO, ServiceModelPutDTO> {

    private PriceConverter priceConverter;
    private CompanyRepository companyRepository;
    private ServiceModelRepository serviceModelRepository;

    public ServiceModelConverter() {
    }


    public ServiceModelGetDTO convertToDTO(ServiceModel serviceModel) {
        if (serviceModel == null) {
            return null;
        }
        PriceGetDTO priceGetDTO = priceConverter.convertToDTO(serviceModel.getPriceSuggestion());
        ServiceModelGetDTO dto = new ServiceModelGetDTO();
        dto.setId(serviceModel.getId());
        dto.setName(serviceModel.getName());
        dto.setQuantityUnit(serviceModel.getQuantityUnit());
        dto.setPriceSuggestion(priceGetDTO);
        return dto;
    }

    public ServiceModel convertFromDTO(ServiceModelPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Company company = companyRepository.findById(companyId).get();
        Price price = priceConverter.convertFromDTO(dto.getPriceSuggestion());
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setName(dto.getName());
        serviceModel.setQuantityUnit(dto.getQuantityUnit());
        serviceModel.setCompany(company);
        serviceModel.setPriceSuggestion(price);
        return serviceModel;
    }

    public ServiceModel convertFromDTO(ServiceModelPutDTO dto, Long serviceModelId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        ServiceModel serviceModel = serviceModelRepository.findById(serviceModelId).get();
        Long priceId = serviceModel.getPriceSuggestion().getId();
        PricePutDTO priceDto = dto.getPriceSuggestion();
        Price price = priceConverter.convertFromDTO(priceDto, priceId);
        serviceModel.setName(dto.getName());
        serviceModel.setQuantityUnit(dto.getQuantityUnit());
        serviceModel.setPriceSuggestion(price);
        return serviceModel;
    }

    @Autowired
    public void setPriceConverter(PriceConverter priceConverter) {
        this.priceConverter = priceConverter;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setServiceModelRepository(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }
}
