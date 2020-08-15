package dev.szafraniak.bmresource.dto.entity.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.szafraniak.bmresource.dto.GetDTOInterface;
import dev.szafraniak.bmresource.dto.entity.address.AddressGetDTO;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyGetDTO implements GetDTOInterface {

    private Long id;

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
