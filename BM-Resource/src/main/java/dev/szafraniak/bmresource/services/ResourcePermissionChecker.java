package dev.szafraniak.bmresource.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourcePermissionChecker {

    private UserService userService;

    public boolean checkUserId(Long userId) {
        return userService.getOrCreateContextUser().getId().equals(userId);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
