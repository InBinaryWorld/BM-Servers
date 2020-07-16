package dev.szafraniak.bmresource.dto.productGroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.szafraniak.bmresource.dto.shared.BaseGetDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductGroupPostDTO {

//    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 50)
    @Pattern(regexp = Regexps.ALMOST_ALL_CHARACTERS)
    private String name;

    @JsonIgnore
    private List<BaseGetDTO> productModels = new ArrayList<>();

//    private Company company;

}
