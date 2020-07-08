package dev.szafraniak.bmresource.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class UserCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_company_gen")
    private Long id;

    @NotNull
    private Long nextInvoiceNumber;

    @NotNull
    private String name;

    @NotNull
    private String invoicePrefix;

    @NotNull
    private String taxIdentityNumber;

    @NotNull
    private String currency;

    @NotNull
    @ManyToOne
    @JsonBackReference
    private User owner;

    @NotNull
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Address headquarter;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Warehouse> warehouses;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProductGroup> productGroups;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProductModel> productModels;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ServiceModel> serviceModels;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Invoice> invoices;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Employee> workers;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FinancialRow> financialHistory;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PaymentMethod> paymentMethods;
}
