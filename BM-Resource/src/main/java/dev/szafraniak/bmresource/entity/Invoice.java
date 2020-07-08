package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_gen")
    private Long id;

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

    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Amount totalAmount;

    @ManyToOne
    @JsonManagedReference
    private BusinessContact businessContact;

}
