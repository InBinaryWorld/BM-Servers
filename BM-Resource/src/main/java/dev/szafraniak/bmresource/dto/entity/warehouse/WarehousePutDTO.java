package dev.szafraniak.bmresource.dto.entity.warehouse;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import dev.szafraniak.bmresource.dto.entity.address.AddressPutDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class WarehousePutDTO implements PutDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressPutDTO address;

//    private List<Product> products;

//    private Company company;

}
