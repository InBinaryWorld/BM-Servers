package dev.szafraniak.bmresource.controller.entity;

import dev.szafraniak.bmresource.dto.entity.user.UserGetDTO;
import dev.szafraniak.bmresource.services.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService service;

    @GetMapping
    public UserGetDTO getEntity() {
        return service.getUser();
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }
}

