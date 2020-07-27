package dev.szafraniak.bmresource.dto.action.createInvoice;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CreateInvoiceDTO {

    @Valid
    @NotNull
    private InvoiceContactDTO buyer;

    @Valid
    private InvoiceContactDTO receiver;

    @Pattern(regexp = Regexps.BANK_ACCOUNT)
    private String bankAccount;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private OffsetDateTime creationDate;

    @Valid
    @NotNull
    @NotEmpty
    private List<InvoiceOrderItemDTO> items;
}
