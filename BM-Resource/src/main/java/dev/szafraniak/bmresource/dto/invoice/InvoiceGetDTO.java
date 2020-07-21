package dev.szafraniak.bmresource.dto.invoice;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.amount.AmountGetDTO;
import dev.szafraniak.bmresource.dto.shared.BaseGetDTO;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceGetDTO implements GetDTOInterface {

    private Long id;

    private String buyerName;

    private String fileReference;

    private String state;

    private String invoiceName;

    private OffsetDateTime creationDate;

    private String dueDate;

    private BaseGetDTO contact;

    private AmountGetDTO totalAmount;

//    private Company company;

}
