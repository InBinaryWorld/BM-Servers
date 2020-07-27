package dev.szafraniak.bmresource.model.action;


import lombok.Data;

import java.util.List;

@Data
public class InvoiceContactModel {

    private String name;
    private String taxIdentityNumber;
    private List<String> addressRows;
}
