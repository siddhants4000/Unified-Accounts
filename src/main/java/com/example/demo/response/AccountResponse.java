package com.example.demo.response;

import com.example.demo.entity.User;
import com.example.demo.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("account_id")
    private UUID accountId;

    @JsonProperty("account_status")
    private String accountStatus;

    @JsonProperty("account_type")
    private AccountType accountType;

    @JsonProperty("account_balance")
    private double accountBalance;

    @JsonProperty("creation_date_time")
    private LocalDateTime creationDateTime;

}
