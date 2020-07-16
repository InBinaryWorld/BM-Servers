package dev.szafraniak.bmresource.dto.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseGetDTO {

    private Long id;

    private String name;
}
