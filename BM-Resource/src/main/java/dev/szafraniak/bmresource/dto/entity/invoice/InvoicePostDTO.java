package dev.szafraniak.bmresource.dto.entity.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.entity.amount.AmountPostDTO;
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

    @JsonIgnore
    private Boolean isPaid = false;

    @NotNull
    private String invoiceName;

    @NotNull
    private OffsetDateTime creationDate;

//    private OffsetDateTime dateOfPayment;
    
    @NotNull
    private LocalDate dueDate;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private LocalDate sellDate;

    @NotNull
    private Boolean splitPayment;

    @Valid
    @NotNull
    private AmountPostDTO totalAmount;

//    private Company company;

}
