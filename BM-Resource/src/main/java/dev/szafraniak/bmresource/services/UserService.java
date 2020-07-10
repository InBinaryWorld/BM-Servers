package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.converters.UserConverter;
import dev.szafraniak.bmresource.dto.user.UserGetDTO;
import dev.szafraniak.bmresource.entity.User;
import dev.szafraniak.bmresource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserConverter userConverter;
    private UserRepository userRepository;

    public UserGetDTO getUser() {
        User user = getOrCreateContextUser();
        return userConverter.convertToDTO(user);
    }

    public User getOrCreateContextUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> userOptional = userRepository.findFirstByKeycloakId(auth.getName());
        return userOptional.orElse(createNewUser(auth.getName()));
    }

    public Long getContextUserId() {
        return getOrCreateContextUser().getId();
    }

    private User createNewUser(String keycloakId) {
        User user = new User();
        user.setKeycloakId(keycloakId);
        return userRepository.save(user);
    }

    @Autowired
    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Autowired
    private void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
