package dev.szafraniak.bmresource.dto.entity.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.amount.AmountGetDTO;
import dev.szafraniak.bmresource.dto.entity.shared.BaseGetDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceGetDTO implements GetDTOInterface {

    private Long id;

    private String buyerName;

    private String fileReference;

    private Boolean isPaid;

    private String invoiceName;

    private OffsetDateTime creationDate;

    private LocalDate dueDate;

    private BaseGetDTO contact;

    private AmountGetDTO totalAmount;

//    private Company company;

}
