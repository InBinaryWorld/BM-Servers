package dev.szafraniak.bmresource.converters.entity;

import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import dev.szafraniak.bmresource.dto.entity.user.UserGetDTO;
import dev.szafraniak.bmresource.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private CompanyConverter companyConverter;

    public UserGetDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        List<BaseGetDTO> companies = user.getCompanies().stream()
                .map(companyConverter::convertToBaseDTO)
                .collect(Collectors.toList());
        UserGetDTO userDto = new UserGetDTO();
        userDto.setId(user.getId());
        userDto.setCompanies(companies);
        return userDto;
    }

    @Autowired
    public void setCompanyConverter(CompanyConverter companyConverter) {
        this.companyConverter = companyConverter;
    }
}
