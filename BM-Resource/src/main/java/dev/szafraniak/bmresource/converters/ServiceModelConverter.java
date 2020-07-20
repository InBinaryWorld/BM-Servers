package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.converters.interfaces.ConverterCompanyInterface;
import dev.szafraniak.bmresource.dto.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.price.PricePutDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.Price;
import dev.szafraniak.bmresource.entity.ServiceModel;
import dev.szafraniak.bmresource.repository.entity.CompanyRepository;
import dev.szafraniak.bmresource.repository.entity.ServiceModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceModelConverter implements ConverterCompanyInterface<ServiceModel, ServiceModelGetDTO, ServiceModelPostDTO, ServiceModelPutDTO> {

    private PriceConverter priceConverter;
    private CompanyRepository companyRepository;
    private ServiceModelRepository serviceModelRepository;


    public ServiceModelGetDTO convertToDTO(ServiceModel serviceModel) {
        if (serviceModel == null) {
            return null;
        }
        PriceGetDTO priceGetDTO = priceConverter.convertToDTO(serviceModel.getPriceSuggestion());
        ServiceModelGetDTO dto = new ServiceModelGetDTO();
        dto.setId(serviceModel.getId());
        dto.setName(serviceModel.getName());
        dto.setQuantityUnitId(serviceModel.getQuantityUnitId());
        dto.setPriceSuggestion(priceGetDTO);
        return dto;
    }

    public ServiceModel convertFromDTO(ServiceModelPostDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyRepository.findById(companyId).get();
        Price price = priceConverter.convertFromDTO(dto.getPriceSuggestion());
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setName(dto.getName());
        serviceModel.setQuantityUnitId(dto.getQuantityUnitId());
        serviceModel.setCompany(company);
        serviceModel.setPriceSuggestion(price);
        return serviceModel;
    }

    public ServiceModel convertFromDTO(ServiceModelPutDTO dto, Long serviceModelId) {
        if (dto == null) {
            return null;
        }
        ServiceModel serviceModel = serviceModelRepository.findById(serviceModelId).get();
        Long priceId = serviceModel.getPriceSuggestion().getId();
        PricePutDTO priceDto = dto.getPriceSuggestion();
        Price price = priceConverter.convertFromDTO(priceDto, priceId);
        serviceModel.setName(dto.getName());
        serviceModel.setQuantityUnitId(dto.getQuantityUnitId());
        serviceModel.setPriceSuggestion(price);
        return serviceModel;
    }

    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public void setPriceConverter(PriceConverter priceConverter) {
        this.priceConverter = priceConverter;
    }

    @Autowired
    public void setServiceModelRepository(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }
}
