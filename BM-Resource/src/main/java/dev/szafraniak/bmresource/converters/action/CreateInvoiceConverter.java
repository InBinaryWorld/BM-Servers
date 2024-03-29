package dev.szafraniak.bmresource.converters.action;

import dev.szafraniak.bmresource.dto.action.createInvoice.*;
import dev.szafraniak.bmresource.dto.action.createInvoice.payment.PaymentMethodCashDTO;
import dev.szafraniak.bmresource.dto.action.createInvoice.payment.PaymentMethodDTO;
import dev.szafraniak.bmresource.dto.action.createInvoice.payment.PaymentMethodTransferDTO;
import dev.szafraniak.bmresource.dto.entity.amount.AmountPostDTO;
import dev.szafraniak.bmresource.dto.entity.invoice.InvoicePostDTO;
import dev.szafraniak.bmresource.model.action.contact.InvoiceCompanyContactModel;
import dev.szafraniak.bmresource.model.action.contact.InvoiceContactModel;
import dev.szafraniak.bmresource.model.action.contact.InvoiceIndividualContactModel;
import dev.szafraniak.bmresource.model.action.invoice.AmountModel;
import dev.szafraniak.bmresource.model.action.invoice.BaseInvoiceDataModel;
import dev.szafraniak.bmresource.model.action.invoice.FinancesInvoiceSectionModel;
import dev.szafraniak.bmresource.model.action.invoice.InvoiceOrderItemModel;
import dev.szafraniak.bmresource.model.entity.Company;
import dev.szafraniak.bmresource.model.entity.payment.PaymentMethod;
import dev.szafraniak.bmresource.model.entity.payment.PaymentMethodCash;
import dev.szafraniak.bmresource.model.entity.payment.PaymentMethodTransfer;
import dev.szafraniak.bmresource.services.entity.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateInvoiceConverter {

    private CompanyService companyService;

    public BaseInvoiceDataModel convertToModel(CreateInvoiceDTO dto, Long companyId) {
        if (dto == null) {
            return null;
        }
        Company company = companyService.getEntity(companyId);
        InvoiceContactModel seller = convertToModel(company);
        InvoiceContactModel buyer = convertToModel(dto.getBuyer());
        InvoiceContactModel receiver = convertToModel(dto.getReceiver());
        PaymentMethod paymentMethod = convertToModel(dto.getPaymentMethod());

        BaseInvoiceDataModel model = new BaseInvoiceDataModel();
        model.setInvoiceLogo(company.getInvoiceLogo());
        model.setInvoiceNumber(dto.getInvoiceNumber());
        model.setCreationDate(dto.getCreationDate());
        model.setSplitPayment(dto.getSplitPayment());
        model.setPaymentMethod(paymentMethod);
        model.setDueDate(dto.getDueDate());
        model.setIssueDate(dto.getIssueDate());
        model.setSellDate(dto.getSellDate());
        model.setReceiver(receiver);
        model.setSeller(seller);
        model.setBuyer(buyer);
        return model;
    }

    private PaymentMethod convertToModel(PaymentMethodDTO paymentMethod) {
        if (paymentMethod instanceof PaymentMethodCashDTO) {
            return convertToModel((PaymentMethodCashDTO) paymentMethod);
        } else if (paymentMethod instanceof PaymentMethodTransferDTO) {
            return convertToModel((PaymentMethodTransferDTO) paymentMethod);
        }
        return null;
    }

    private PaymentMethodCash convertToModel(PaymentMethodCashDTO paymentMethod) {
        return new PaymentMethodCash();
    }

    private PaymentMethodTransfer convertToModel(PaymentMethodTransferDTO paymentMethod) {
        PaymentMethodTransfer payment = new PaymentMethodTransfer();
        payment.setBankAccount(paymentMethod.getAccountNumber());
        return payment;
    }

    public InvoiceOrderItemModel convertToModel(InvoiceOrderItemDTO dto, AmountModel amount) {
        InvoiceOrderItemModel model = new InvoiceOrderItemModel();
        model.setName(dto.getName());
        model.setQuantityUnit(dto.getQuantityUnit());
        model.setQuantity(dto.getQuantity().stripTrailingZeros());
        model.setTaxRate(dto.getTaxRate());
        model.setPriceNet(dto.getNetPrice());
        model.setNet(amount.getNet());
        model.setTax(amount.getTax());
        model.setGross(amount.getGross());
        return model;
    }

    public InvoiceCompanyContactModel convertToModel(Company company) {
        if (company == null) {
            return null;
        }
        List<String> addressRows = company.getHeadquarter().getAddressRows();
        InvoiceCompanyContactModel model = new InvoiceCompanyContactModel();
        model.setName(company.getName());
        model.setTaxIdentityNumber(company.getTaxIdentityNumber());
        model.setAddressRows(addressRows);
        return model;
    }

    private InvoiceContactModel convertToModel(InvoiceContactDTO contact) {
        if (contact instanceof InvoiceIndividualContactDTO) {
            return convertToModel((InvoiceIndividualContactDTO) contact);
        } else if (contact instanceof InvoiceCompanyContactDTO) {
            return convertToModel((InvoiceCompanyContactDTO) contact);
        }
        return null;
    }

    private InvoiceCompanyContactModel convertToModel(InvoiceCompanyContactDTO contact) {
        List<String> addressRows = contact.getAddress().getAddressRows();
        InvoiceCompanyContactModel model = new InvoiceCompanyContactModel();
        model.setName(contact.getName());
        model.setTaxIdentityNumber(contact.getTaxIdentityNumber());
        model.setAddressRows(addressRows);
        return model;
    }

    private InvoiceIndividualContactModel convertToModel(InvoiceIndividualContactDTO contact) {
        List<String> addressRows = contact.getAddress().getAddressRows();
        InvoiceIndividualContactModel model = new InvoiceIndividualContactModel();
        model.setFirstName(contact.getFirstName());
        model.setLastName(contact.getLastName());
        model.setAddressRows(addressRows);
        return model;
    }

    public InvoicePostDTO convertToPostDTO(BaseInvoiceDataModel model, FinancesInvoiceSectionModel finances, String fileName) {
        String receiverName = model.getReceiver() == null ? null : model.getReceiver().getName();
        AmountModel totalAmount = finances.getTotalAmount();
        AmountPostDTO amountPostDTO = new AmountPostDTO();
        amountPostDTO.setNet(totalAmount.getNet());
        amountPostDTO.setTax(totalAmount.getTax());
        amountPostDTO.setGross(totalAmount.getGross());

        InvoicePostDTO dto = new InvoicePostDTO();
        dto.setReceiverName(receiverName);
        dto.setBuyerName(model.getBuyer().getName());
        dto.setCreationDate(model.getCreationDate());
        dto.setInvoiceName(model.getInvoiceNumber());
        dto.setSplitPayment(model.getSplitPayment());
        dto.setDueDate(model.getDueDate());
        dto.setIssueDate(model.getIssueDate());
        dto.setSellDate(model.getSellDate());
        dto.setTotalAmount(amountPostDTO);
        dto.setFileReference(fileName);
        dto.setIsPaid(false);
        return dto;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
