package dev.szafraniak.bmresource.dto.user;

import dev.szafraniak.bmresource.dto.company.UserCompanyResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private List<UserCompanyResponseDTO> companies;
}
