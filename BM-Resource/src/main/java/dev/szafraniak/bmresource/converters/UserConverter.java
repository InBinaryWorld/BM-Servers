package dev.szafraniak.bmresource.converters;

import dev.szafraniak.bmresource.dto.user.UserGetDTO;
import dev.szafraniak.bmresource.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserGetDTO convertToDTO(User user) {
        return modelMapper.map(user, UserGetDTO.class);
    }
}
