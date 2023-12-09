package com.example.demo.response;

import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UnifiedAccountResponse {

    @Column(name = "total_account_balance")
    private double totalAccountBalance;

    @Column(name = "total_accounts")
    private double totalAccounts;

}
