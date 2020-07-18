package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.amount.AmountGetDTO;
import dev.szafraniak.bmresource.entity.Amount;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmountConverter {

    private ModelMapper modelMapper;

    public AmountGetDTO convertToDTO(Amount amount) {
        return modelMapper.map(amount, AmountGetDTO.class);
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
