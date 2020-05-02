package dev.szafraniak.bmresourceserver.rest.v1.entity.user;

import lombok.Data;

@Data
public class BankAccount {
    private String id;
    private String displayName;
    private String accountNumber;
}
