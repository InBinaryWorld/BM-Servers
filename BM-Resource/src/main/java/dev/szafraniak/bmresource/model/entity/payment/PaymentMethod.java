package dev.szafraniak.bmresource.model.entity.payment;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PaymentMethodCash.class, name = "cash"),
        @JsonSubTypes.Type(value = PaymentMethodTransfer.class, name = "transfer")
})
public interface PaymentMethod {
}
