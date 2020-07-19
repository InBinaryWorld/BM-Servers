package dev.szafraniak.bmresource.dto.contact;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.address.AddressGetDTO;
import dev.szafraniak.bmresource.dto.invoice.InvoiceGetDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactGetDTO {

    private Long id;

    private String name;

    private String phone;

    private AddressGetDTO address;

    private List<InvoiceGetDTO> invoices;

//    private Company company;

}
