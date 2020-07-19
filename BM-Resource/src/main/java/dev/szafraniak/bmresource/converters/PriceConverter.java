package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.price.PriceGetDTO;
import dev.szafraniak.bmresource.dto.price.PricePostDTO;
import dev.szafraniak.bmresource.dto.price.PricePutDTO;
import dev.szafraniak.bmresource.entity.Price;
import dev.szafraniak.bmresource.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceConverter {

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
