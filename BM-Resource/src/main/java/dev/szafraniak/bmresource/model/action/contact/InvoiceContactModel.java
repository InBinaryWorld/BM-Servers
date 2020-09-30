package dev.szafraniak.bmresource.model.action.contact;


import lombok.Data;

import java.util.List;

@Data
public abstract class InvoiceContactModel {
    private List<String> addressRows;

    public abstract String getName();
}
