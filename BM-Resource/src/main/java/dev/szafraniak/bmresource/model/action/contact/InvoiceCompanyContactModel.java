package dev.szafraniak.bmresource.model.action.contact;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvoiceCompanyContactModel extends InvoiceContactModel {
    private String name;
    private String taxIdentityNumber;
}
