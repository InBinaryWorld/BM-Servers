package dev.szafraniak.bmresource.dto.entity.invoice;

import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.entity.amount.AmountPostDTO;
import dev.szafraniak.bmresource.model.entity.contact.Contact;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
public class InvoicePostDTO implements PostDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String buyerName;

    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String receiverName;

    @NotNull
    private String fileReference;

    @NotNull
    private String state;

    @NotNull
    private String invoiceName;

    @NotNull
    private OffsetDateTime creationDate;

    @NotNull
    private LocalDate dueDate;

    @Valid
    private Contact contact;

    @Valid
    @NotNull
    private AmountPostDTO totalAmount;

//    private Company company;

}
