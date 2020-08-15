package dev.szafraniak.bmresource.converters.action;

import dev.szafraniak.bmresource.dto.action.createInvoice.CreateInvoiceDTO;
import dev.szafraniak.bmresource.dto.action.createInvoice.InvoiceContactDTO;
import dev.szafraniak.bmresource.dto.action.createInvoice.InvoiceOrderItemDTO;
import dev.szafraniak.bmresource.dto.entity.amount.AmountPostDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.model.action.CreateInvoiceModel;
import dev.szafraniak.bmresource.model.action.InvoiceContactModel;
import dev.szafraniak.bmresource.model.action.InvoiceDetailsModel;
import dev.szafraniak.bmresource.model.action.InvoiceOrderItemModel;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.services.entity.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreateInvoiceConverter {

    private CompanyService companyService;

    public CreateInvoiceModel convertToModel(CreateInvoiceDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyService.getEntity(companyId);
        InvoiceContactModel seller = convertToModel(company);
        InvoiceContactModel buyer = convertToModel(dto.getBuyer());
        InvoiceContactModel receiver = convertToModel(dto.getReceiver());

        CreateInvoiceModel model = new CreateInvoiceModel();
        model.setBankAccount(dto.getBankAccount());
        model.setCreationDate(dto.getCreationDate());
        model.setDueDate(dto.getDueDate());
        model.setInvoiceNumber(dto.getInvoiceNumber());
        model.setBuyer(buyer);
        model.setReceiver(receiver);
        model.setSeller(seller);
        return model;
    }

    public InvoiceOrderItemModel convertToModel(InvoiceOrderItemDTO dto, BigDecimal totalNet,
                                                BigDecimal totalTax, BigDecimal totalGross) {
        InvoiceOrderItemModel model = new InvoiceOrderItemModel();
        model.setName(dto.getName());
        model.setQuantityUnit(dto.getQuantityUnit());
        model.setQuantity(dto.getQuantity());
        model.setTaxRate(dto.getTaxRate());
        model.setPriceNet(dto.getNetPrice());
        model.setNet(totalNet);
        model.setTax(totalTax);
        model.setGross(totalGross);
        return model;
    }

    public InvoiceContactModel convertToModel(Company company) {
        if (company == null) {
            return null;
        }
        List<String> addressRows = company.getHeadquarter().getAddressRows();
        InvoiceContactModel model = new InvoiceContactModel();
        model.setName(company.getName());
        model.setTaxIdentityNumber(company.getTaxIdentityNumber());
        model.setAddressRows(addressRows);
        return model;
    }

    private InvoiceContactModel convertToModel(InvoiceContactDTO contact) {
        if (contact == null) {
            return null;
        }
        List<String> addressRows = contact.getAddress().getAddressRows();
        InvoiceContactModel model = new InvoiceContactModel();
        model.setName(contact.getName());
        model.setTaxIdentityNumber(contact.getTaxIdentityNumber());
        model.setAddressRows(addressRows);
        return model;
    }

    public InvoicePostDTO convertToPostDTO(CreateInvoiceModel model, InvoiceDetailsModel details, String fileName) {
        String receiverName = model.getReceiver() == null ? null : model.getReceiver().getName();
        AmountPostDTO amountPostDTO = new AmountPostDTO();
        amountPostDTO.setNet(details.getTotalNet());
        amountPostDTO.setTax(details.getTotalTax());
        amountPostDTO.setGross(details.getTotalGross());

        InvoicePostDTO dto = new InvoicePostDTO();
        dto.setReceiverName(receiverName);
        dto.setBuyerName(model.getBuyer().getName());
        dto.setCreationDate(model.getCreationDate());
        dto.setInvoiceName(model.getInvoiceNumber());
        dto.setDueDate(model.getDueDate());
        dto.setTotalAmount(amountPostDTO);
        dto.setFileReference(fileName);
        dto.setContact(null);
        dto.setState("NEW");
        return dto;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
