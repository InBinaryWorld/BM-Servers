package dev.szafraniak.bmresource.dto.company;

import dev.szafraniak.bmresource.dto.shared.IdDTO;
import lombok.Data;

@Data
public class UserCompanyResponseDTO {
    private Long id;
    private String name;
    private String invoicePrefix;
    private String taxIdentityNumber;
    private String currency;
    private IdDTO owner;
//    private List<WarehouseResponseDTO> warehouses;
//    private List<ProductGroupResponseDTO> productGroups;
//    private List<ProductModelResponseDTO> productModels;
//    private List<ServiceModelResponseDTO> serviceModels;
//    private List<InvoiceResponseDTO> invoices;
//    private List<EmployeeResponseDTO> workers;
//    private List<FinancialRowResponseDTO> financialHistory;
//    private List<PaymentMethodResponseDTO> paymentMethods;
}

