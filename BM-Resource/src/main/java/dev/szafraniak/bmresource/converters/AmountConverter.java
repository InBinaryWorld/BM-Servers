package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.amount.AmountGetDTO;
import dev.szafraniak.bmresource.entity.Amount;
import org.springframework.stereotype.Component;

@Component
public class AmountConverter {

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

}
