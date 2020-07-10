package dev.szafraniak.bmresource.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long nextInvoiceNumber;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;

    @NotNull
    @Pattern(regexp = "\\S{2,14}")
    private String invoicePrefix;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String taxIdentityNumber;

    @NotNull
    @Pattern(regexp = "[A-Z]]{2,4}")
    private String currency;

    @NotNull
    @ManyToOne
    private User owner;

    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Address headquarter;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<Warehouse> warehouses;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<ProductGroup> productGroups;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<ProductModel> productModels;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<ServiceModel> serviceModels;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<Invoice> invoices;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<BusinessContact> contacts;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<Employee> workers;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<FinancialRow> financialHistory;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<PaymentMethod> paymentMethods;
}
