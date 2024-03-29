package dev.szafraniak.bmresource.dto.entity.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.szafraniak.bmresource.config.BaseEnvironment;
import dev.szafraniak.bmresource.dto.PostDTOInterface;
import dev.szafraniak.bmresource.dto.entity.address.AddressPostDTO;
import dev.szafraniak.bmresource.utils.Regexps;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class CompanyPostDTO implements PostDTOInterface {

//    private Long id;

    @NotNull
    @NotBlank
    @Pattern(regexp = Regexps.BASE_2_40)
    private String name;

    @Pattern(regexp = Regexps.INVOICE_PREFIX_2_14)
    private String invoicePrefix;

    @Length(max = 20)
    @Pattern(regexp = Regexps.BASE_1_20)
    private String invoiceLogo;

    @NotNull
    @Pattern(regexp = Regexps.TAX_IDENTITY_NUMBER)
    private String taxIdentityNumber;

    @NotNull
    @JsonIgnore
    @Length(min = 2, max = 4)
    @Pattern(regexp = Regexps.CURRENCY)
    private String currency = BaseEnvironment.CURRENCY;

//    private User owner;

    @Valid
    @NotNull
    private AddressPostDTO headquarter;

//    private List<Warehouse> warehouses;

//    private List<ProductModel> productModels;

//    private List<ServiceModel> serviceModels;

//    private List<Invoice> invoices;

//    private List<Contact> contacts;

//    private List<FinancialRow> financialHistory;

//    private List<PaymentMethod> paymentMethods;
}
