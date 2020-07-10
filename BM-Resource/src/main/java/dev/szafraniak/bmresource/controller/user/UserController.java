package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.dto.user.UserGetDTO;
import dev.szafraniak.bmresource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService userService;


    @GetMapping
    public UserGetDTO getUser() {
        return userService.getUser();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}

