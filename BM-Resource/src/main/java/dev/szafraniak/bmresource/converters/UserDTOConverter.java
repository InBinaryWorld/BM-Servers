package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.user.UserResponseDTO;
import dev.szafraniak.bmresource.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter {

    ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponseDTO convertToUserResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
