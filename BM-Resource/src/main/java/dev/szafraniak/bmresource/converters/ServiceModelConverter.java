package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.price.PricePutDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelGetDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPostDTO;
import dev.szafraniak.bmresource.dto.serviceModel.ServiceModelPutDTO;
import dev.szafraniak.bmresource.entity.Company;
import dev.szafraniak.bmresource.entity.Price;
import dev.szafraniak.bmresource.entity.ServiceModel;
import dev.szafraniak.bmresource.repository.CompanyRepository;
import dev.szafraniak.bmresource.repository.ServiceModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceModelConverter {

    private ModelMapper modelMapper;
    private PriceConverter priceConverter;
    private CompanyRepository companyRepository;
    private ServiceModelRepository serviceModelRepository;


    public ServiceModelGetDTO convertToDTO(ServiceModel serviceModel) {
        return modelMapper.map(serviceModel, ServiceModelGetDTO.class);
    }

    public ServiceModel convertFromDTO(ServiceModelPostDTO dto, Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        Price price = priceConverter.convertFromDTO(dto.getPriceSuggestion());
        ServiceModel serviceModel = modelMapper.map(dto, ServiceModel.class);
        serviceModel.setCompany(company);
        serviceModel.setPriceSuggestion(price);
        return serviceModel;
    }

    public ServiceModel convertFromDTO(ServiceModelPutDTO dto, Long serviceModelId) {
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
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
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
