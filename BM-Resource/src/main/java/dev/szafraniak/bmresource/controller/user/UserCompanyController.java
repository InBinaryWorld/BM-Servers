package dev.szafraniak.bmresource.controller.user;

import dev.szafraniak.bmresource.converters.UserCompanyDTOConverter;
import dev.szafraniak.bmresource.dto.company.UserCompanyResponseDTO;
import dev.szafraniak.bmresource.entity.UserCompany;
import dev.szafraniak.bmresource.repository.UserCompanyRepository;
import dev.szafraniak.bmresource.utils.CollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class UserCompanyController {

    private UserCompanyRepository userCompanyRepository;
    private UserCompanyDTOConverter userCompanyDTOConverter;


    @GetMapping("/{userId}/companies")
    @PreAuthorize("@resourcePermissionChecker.checkUserId(#userId)")
    public CollectionEntity<UserCompanyResponseDTO> getCompany(@PathVariable Long userId) {
        List<UserCompany> companies = userCompanyRepository.findAllByOwner_Id(userId);
        List<UserCompanyResponseDTO> companyDTOs = companies.stream()
                .map(userCompanyDTOConverter::convertToUserCompanyResponseDTO)
                .collect(Collectors.toList());
        return new CollectionEntity<>(companyDTOs);
    }

    @Autowired
    public void setUserCompanyRepository(UserCompanyRepository userCompanyRepository) {
        this.userCompanyRepository = userCompanyRepository;
    }

    @Autowired
    public void setUserCompanyDTOConverter(UserCompanyDTOConverter userCompanyDTOConverter) {
        this.userCompanyDTOConverter = userCompanyDTOConverter;
    }

}

