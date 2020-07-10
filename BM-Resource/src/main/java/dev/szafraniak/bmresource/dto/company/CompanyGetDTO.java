package dev.szafraniak.bmresource.dto.company;

import dev.szafraniak.bmresource.dto.address.AddressGetDTO;
import lombok.Data;

@Data
public class CompanyGetDTO {

    private Long id;

//    private Long nextInvoiceNumber;

    private String name;

    private String invoicePrefix;

    private String taxIdentityNumber;

    private String currency;

//    private User owner;

    private AddressGetDTO headquarter;

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
