package dev.szafraniak.bmresource.dto.company;

import dev.szafraniak.bmresource.dto.address.AddressPutDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CompanyPutDTO {

//    private Long id;

//    private Long nextInvoiceNumber;

    @NotNull
    @Length(min = 1, max = 30)
    @Pattern(regexp = Regexps.WORDS_WITH_NUMBERS)
    private String name;

    @NotNull
    @Pattern(regexp = Regexps.INVOICE_PREFIX_2_14)
    private String invoicePrefix;

    @NotNull
    @Pattern(regexp = Regexps.NUMBERS_10)
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
