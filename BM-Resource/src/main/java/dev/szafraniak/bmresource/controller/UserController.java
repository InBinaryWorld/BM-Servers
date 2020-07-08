package dev.szafraniak.bmresource.controller;

import dev.szafraniak.bmresource.entity.User;
import dev.szafraniak.bmresource.repository.UserRepository;
import dev.szafraniak.bmresource.utils.CollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserRepository userRepository;

    @PostMapping("/create")
    private User createUser(Principal principal, @RequestBody User user) {
        user.setId(null);
        user.setKeycloakId(principal.getName());
        return userRepository.save(user);
    }

    @GetMapping
    private CollectionEntity<User> getUser(Principal principal) {
        List<User> users = userRepository.findAllByKeycloakId(principal.getName());
        return new CollectionEntity<>(users);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

