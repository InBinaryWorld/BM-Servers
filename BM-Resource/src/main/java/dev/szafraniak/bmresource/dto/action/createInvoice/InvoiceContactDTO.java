package dev.szafraniak.bmresource.dto.action.createInvoice;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InvoiceCompanyContactDTO.class, name = "company"),
        @JsonSubTypes.Type(value = InvoiceIndividualContactDTO.class, name = "individual")
})
public abstract class InvoiceContactDTO {

    @Valid
    @NotNull
    private InvoiceAddressDTO address;

    public abstract String getName();

    public abstract String getTaxIdentityNumber();
}
