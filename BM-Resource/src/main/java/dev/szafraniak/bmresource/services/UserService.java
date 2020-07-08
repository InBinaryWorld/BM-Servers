package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.entity.User;
import dev.szafraniak.bmresource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public User getOrCreateContextUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findFirstByKeycloakId(auth.getName());
        return userOptional.orElse(createNewUser(auth.getName()));
    }

    private User createNewUser(String keycloakId) {
        User user = new User();
        user.setKeycloakId(keycloakId);
        user.setCompanies(new ArrayList<>());
        return userRepository.save(user);
    }


    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
