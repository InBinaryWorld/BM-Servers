package dev.szafraniak.bmresource.entity;

import dev.szafraniak.bmresource.entity.base.BaseCompanyEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@Entity
public class Invoice extends BaseCompanyEntity {

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
    private OffsetDateTime creationDate;

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
