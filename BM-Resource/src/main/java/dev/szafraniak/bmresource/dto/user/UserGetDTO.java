package dev.szafraniak.bmresource.dto.user;

import dev.szafraniak.bmresource.dto.shared.BaseGetDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserGetDTO {

    private Long id;

    private List<BaseGetDTO> companies;
}
