package dev.szafraniak.bmresource.dto.company;

import dev.szafraniak.bmresource.dto.address.AddressPutDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CompanyPutDTO {

//    private Long id;

//    private Long nextInvoiceNumber;

    @NotNull
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;

    @NotNull
    @Pattern(regexp = "\\S{2,14}")
    private String invoicePrefix;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    private String taxIdentityNumber;

//    private String currency;

//    private User owner;

    @Valid
    @NotNull
    private AddressPutDTO headquarter;

//    private List<Warehouse> warehouses;

//    private List<ProductGroup> productGroups;

//    private List<ProductModel> productModels;

//    private List<ServiceModel> serviceModels;

//    private List<Invoice> invoices;

//    private List<BusinessContact> contacts;

//    private List<Employee> workers;

//    private List<FinancialRow> financialHistory;

//    private List<PaymentMethod> paymentMethods;
}
