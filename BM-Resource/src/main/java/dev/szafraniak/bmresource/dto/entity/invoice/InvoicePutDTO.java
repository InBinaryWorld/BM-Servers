package dev.szafraniak.bmresource.dto.entity.invoice;

import dev.szafraniak.bmresource.dto.PutDTOInterface;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class InvoicePutDTO implements PutDTOInterface {

//    private Long id;

//    private String buyerName;

//    private String fileReference;

    @NotNull
    private Boolean isPaid;

//    private String invoiceName;

//    private OffsetDateTime creationDate;

//    private OffsetDateTime dateOfPayment;

//    private LocalDate dueDate;

//    private LocalDate issueDate;

//    private Contact contact;

//    private Amount totalAmount;

}
