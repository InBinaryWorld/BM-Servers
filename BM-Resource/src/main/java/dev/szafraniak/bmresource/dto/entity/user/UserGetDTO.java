package dev.szafraniak.bmresource.dto.entity.user;

import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserGetDTO {

    private Long id;

    private List<BaseGetDTO> companies;
}
