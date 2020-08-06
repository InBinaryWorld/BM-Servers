package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.UserConverter;
import dev.szafraniak.bmresource.dto.entity.user.UserGetDTO;
import dev.szafraniak.bmresource.model.entity.User;
import dev.szafraniak.bmresource.repository.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return userOptional.orElseGet(() -> createNewUser(auth.getName()));
    }

    public Long getContextUserId() {
        return getOrCreateContextUser().getId();
    }

    private User createNewUser(String keycloakId) {
        User user = new User();
        user.setKeycloakId(keycloakId);
        user.setCompanies(new ArrayList<>());
        return userRepository.save(user);
    }

    @Autowired
    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
