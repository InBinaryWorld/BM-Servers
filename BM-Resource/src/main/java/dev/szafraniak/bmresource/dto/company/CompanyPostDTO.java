package dev.szafraniak.bmresource.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.szafraniak.bmresource.dto.address.AddressPostDTO;
import dev.szafraniak.bmresource.entity.*;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


@Data
public class CompanyPostDTO {

//    private Long id;

    @NotNull
    @JsonIgnore
    private Long nextInvoiceNumber = 1L;

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

    @NotNull
    @JsonIgnore
    @Length(min = 2, max = 4)
    @Pattern(regexp = Regexps.CURRENCY)
    private String currency = "PLN";

//    private User owner;

    @Valid
    @NotNull
    private AddressPostDTO headquarter;

    @JsonIgnore
    private List<Warehouse> warehouses = new ArrayList<>();

    @JsonIgnore
    private List<ProductGroup> productGroups = new ArrayList<>();

    @JsonIgnore
    private List<ProductModel> productModels = new ArrayList<>();

    @JsonIgnore
    private List<ServiceModel> serviceModels = new ArrayList<>();

    @JsonIgnore
    private List<Invoice> invoices = new ArrayList<>();

    @JsonIgnore
    private List<BusinessContact> contacts = new ArrayList<>();

    @JsonIgnore
    private List<Employee> workers = new ArrayList<>();

    @JsonIgnore
    private List<FinancialRow> financialHistory = new ArrayList<>();

    @JsonIgnore
    private List<PaymentMethod> paymentMethods = new ArrayList<>();
}
