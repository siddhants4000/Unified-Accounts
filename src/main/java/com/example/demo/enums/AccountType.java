package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum AccountType {

    COLLECTION("COLLECTION"),
    DISBURSEMENT("DISBURSEMENT"),
    REMITTANCE("REMITTANCE");

    String accountType;

    AccountType(String accountType) {
        this.accountType= accountType;
    }
}
