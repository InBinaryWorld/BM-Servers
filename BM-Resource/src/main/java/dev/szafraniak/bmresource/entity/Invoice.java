package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    private Contact contact;

    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Amount totalAmount;

    @NotNull
    @ManyToOne
    private Company company;

}
