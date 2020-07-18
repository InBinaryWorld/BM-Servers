package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.price.PricePostDTO;
import dev.szafraniak.bmresource.dto.price.PricePutDTO;
import dev.szafraniak.bmresource.entity.Price;
import dev.szafraniak.bmresource.repository.PriceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceConverter {

    private ModelMapper modelMapper;
    private PriceRepository priceRepository;

    public PriceGetDTO convertToDTO(Price price) {
        return modelMapper.map(price, PriceGetDTO.class);
    }

    public Price convertFromDTO(PricePostDTO dto) {
        return modelMapper.map(dto, Price.class);
    }

    public Price convertFromDTO(PricePutDTO dto, Long priceId) {
        Price price = priceRepository.findById(priceId).get();
        price.setNet(dto.getNet());
        price.setGross(dto.getGross());
        price.setTaxRate(dto.getTaxRate());
        return price;
    }
    
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPriceRepository(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
}
