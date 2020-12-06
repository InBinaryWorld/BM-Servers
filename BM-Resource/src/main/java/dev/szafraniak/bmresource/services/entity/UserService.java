package dev.szafraniak.bmresource.services.entity;

import dev.szafraniak.bmresource.converters.entity.UserConverter;
import dev.szafraniak.bmresource.dto.entity.user.UserGetDTO;
import dev.szafraniak.bmresource.model.entity.User;
import dev.szafraniak.bmresource.repository.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private UserConverter userConverter;
    private UserRepository userRepository;

    public UserGetDTO getUser() {
        User user = getContextUser();
        return userConverter.convertToDTO(user);
    }

    public User getContextUser() {
        String keycloakUserId = getKeycloakUserId();
        Optional<User> userOptional = userRepository.findFirstByKeycloakId(keycloakUserId);
        return userOptional.orElseGet(() -> createNewUser(keycloakUserId));
    }

    public boolean isContextUser(Long userId){
        String keycloakUserId = getKeycloakUserId();
        return userRepository.findById(userId)
                .map(User::getKeycloakId)
                .map(keycloakUserId::equals)
                .orElse(false);
    }

    private User createNewUser(String keycloakId) {
        User user = new User();
        user.setKeycloakId(keycloakId);
        user.setCompanies(new ArrayList<>());
        return userRepository.save(user);
    }

    private String getKeycloakUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        return auth.getName();
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
