package dev.szafraniak.bmresource.dto.action.createInvoice.payment;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaymentMethodCashDTO.class, name = "cash"),
        @JsonSubTypes.Type(value = PaymentMethodTransferDTO.class, name = "transfer")
})
public class PaymentMethodDTO {
}
