package dev.szafraniak.bmresource.dto.entity.invoice;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class InvoicePutDTO implements PutDTOInterface {

//    private Long id;

//    private String buyerName;

//    private String fileReference;

    @NotNull
    @NotBlank
    private String state;

//    private String invoiceName;

//    private OffsetDateTime creationDate;

//    private LocalDate dueDate;

//    private Contact contact;

//    private Amount totalAmount;

//    private Company company;

}
