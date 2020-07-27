package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.amount.AmountGetDTO;
import dev.szafraniak.bmresource.dto.entity.amount.AmountPostDTO;
import dev.szafraniak.bmresource.dto.entity.amount.AmountPutDTO;
import dev.szafraniak.bmresource.model.entity.Amount;
import dev.szafraniak.bmresource.repository.entity.AmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmountConverter implements ConverterInterface<Amount,
        AmountGetDTO, AmountPostDTO, AmountPutDTO> {

    private AmountRepository amountRepository;

    public AmountGetDTO convertToDTO(Amount amount) {
        if (amount == null) {
            return null;
        }
        AmountGetDTO dto = new AmountGetDTO();
        dto.setId(amount.getId());
        dto.setNet(amount.getNet());
        dto.setGross(amount.getGross());
        dto.setTax(amount.getTax());
        return dto;
    }

    public Amount convertFromDTO(AmountPostDTO dto) {
        if (dto == null) {
            return null;
        }
        Amount amount = new Amount();
        amount.setGross(dto.getGross());
        amount.setNet(dto.getNet());
        amount.setTax(dto.getTax());
        return amount;
    }

    public Amount convertFromDTO(AmountPutDTO dto, Long entityId) {
        if (dto == null) {
            return null;
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Amount amount = amountRepository.findById(entityId).get();
        amount.setGross(dto.getGross());
        amount.setNet(dto.getNet());
        amount.setTax(dto.getTax());
        return amount;
    }


    @Autowired
    public void setAmountRepository(AmountRepository amountRepository) {
        this.amountRepository = amountRepository;
    }
}
