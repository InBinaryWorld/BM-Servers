package dev.szafraniak.bmresource.dto.entity.warehouse;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.entity.product.ProductGetDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WarehouseGetDTO implements GetDTOInterface {

    private Long id;

    private String name;

    private AddressGetDTO address;

    private List<ProductGetDTO> products;

    //    private Company company;

}
