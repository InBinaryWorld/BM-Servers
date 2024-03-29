package dev.szafraniak.bmresource.dto.entity.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoiceGetDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactGetDTO implements GetDTOInterface {

    private Long id;

    private String name;

    private String phone;

    private AddressGetDTO address;

    private List<InvoiceGetDTO> invoices;

//    private Company company;

}
