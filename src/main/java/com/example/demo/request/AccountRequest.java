package com.example.demo.request;

import com.example.demo.entity.User;
import com.example.demo.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {

    @JsonIgnore
    private String accountStatus;

    @NotNull
    private AccountType accountType;

    @NotNull
    private double accountBalance;

    @NotNull
    private UUID userId;

    @JsonIgnore
    private LocalDateTime creationDateTime;
}
