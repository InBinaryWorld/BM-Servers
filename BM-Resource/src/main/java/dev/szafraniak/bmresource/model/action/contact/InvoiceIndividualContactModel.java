package dev.szafraniak.bmresource.model.action.contact;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvoiceIndividualContactModel extends InvoiceContactModel {

    private String firstName;
    private String lastName;

    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }
}
