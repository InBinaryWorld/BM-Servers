package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.company.UserCompanyResponseDTO;
import dev.szafraniak.bmresource.entity.UserCompany;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCompanyDTOConverter {

    ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserCompanyResponseDTO convertToUserCompanyResponseDTO(UserCompany userCompany) {
        return modelMapper.map(userCompany, UserCompanyResponseDTO.class);
    }
}
