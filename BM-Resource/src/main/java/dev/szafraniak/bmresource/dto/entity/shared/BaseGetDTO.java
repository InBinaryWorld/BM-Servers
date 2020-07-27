package dev.szafraniak.bmresource.dto.entity.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseGetDTO {

    private Long id;

    private String name;
}
