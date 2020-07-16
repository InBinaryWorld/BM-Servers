package dev.szafraniak.bmresource.dto.shared;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BasePostDTO {

    @NotNull
    private Long id;

    private String name;
}
