package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.converters.UserDTOConverter;
import dev.szafraniak.bmresource.dto.user.UserResponseDTO;
import dev.szafraniak.bmresource.entity.User;
import dev.szafraniak.bmresource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;
    private UserDTOConverter userDTOConverter;


    @GetMapping
    public UserResponseDTO getUser() {
        User user = userService.getOrCreateContextUser();
        return userDTOConverter.convertToUserResponseDTO(user);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDTOConverter(UserDTOConverter userDTOConverter) {
        this.userDTOConverter = userDTOConverter;
    }
}

