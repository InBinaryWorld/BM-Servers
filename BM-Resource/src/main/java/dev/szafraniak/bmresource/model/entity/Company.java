package dev.szafraniak.bmresource.model.entity;

import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @NotNull
    @Pattern(regexp = Regexps.INVOICE_PREFIX_2_14)
    private String invoicePrefix;

    @NotNull
    @Pattern(regexp = Regexps.TAX_IDENTITY_NUMBER)
    private String taxIdentityNumber;

    @NotNull
    @Pattern(regexp = Regexps.CURRENCY)
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
    private List<IndividualContact> individualContacts;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<CompanyContact> companyContacts;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<Employee> workers;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<FinancialRow> financialHistory;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<BankAccount> bankAccounts;
}
