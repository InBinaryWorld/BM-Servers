package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.entity.price.PricePostDTO;
import dev.szafraniak.bmresource.dto.entity.price.PricePutDTO;
import dev.szafraniak.bmresource.model.entity.Price;
import dev.szafraniak.bmresource.repository.entity.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceConverter implements ConverterInterface<Price, PriceGetDTO, PricePostDTO, PricePutDTO> {

    private PriceRepository priceRepository;

    public PriceGetDTO convertToDTO(Price price) {
        if (price == null) {
            return null;
        }
        PriceGetDTO priceGetDTO = new PriceGetDTO();
        priceGetDTO.setId(price.getId());
        priceGetDTO.setNet(price.getNet());
        priceGetDTO.setGross(price.getGross());
        priceGetDTO.setTaxRate(price.getTaxRate());
        return priceGetDTO;
    }

    public Price convertFromDTO(PricePostDTO dto) {
        if (dto == null) {
            return null;
        }
        Price price = new Price();
        price.setNet(dto.getNet());
        price.setGross(dto.getGross());
        price.setTaxRate(dto.getTaxRate());
        return price;
    }

    public Price convertFromDTO(PricePutDTO dto, Long priceId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Price price = priceRepository.findById(priceId).get();
        price.setNet(dto.getNet());
        price.setGross(dto.getGross());
        price.setTaxRate(dto.getTaxRate());
        return price;
    }

    @Autowired
    public void setPriceRepository(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
}