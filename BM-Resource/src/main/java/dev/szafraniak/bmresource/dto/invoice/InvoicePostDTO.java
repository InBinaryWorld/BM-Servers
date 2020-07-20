package dev.szafraniak.bmresource.dto.invoice;

import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.amount.AmountPostDTO;
import dev.szafraniak.bmresource.entity.Contact;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class InvoicePostDTO implements PostDTOInterface {

//    private Long id;

    @NotNull
    private String buyerName;

    @NotNull
    private String fileReference;

    @NotNull
    private String state;

    @NotNull
    private String invoiceName;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private String dueDate;

    @ManyToOne
    private Contact contact;

    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private AmountPostDTO totalAmount;

//    private Company company;

}
