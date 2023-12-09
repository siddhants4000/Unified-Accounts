package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.enums.StatusCode;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.AccountRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.request.AccountRequest;
import com.example.demo.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public WrapperResponse<AccountResponse> addAccount(AccountRequest accountRequest) {
        User user=userRepository.findByUserId(accountRequest.getUserId());
        if (!Objects.isNull(user)) {
            Account newAccount = Account.builder()
                    .accountStatus("ACTIVE")
                    .accountType(accountRequest.getAccountType())
                    .accountBalance(accountRequest.getAccountBalance())
                    .creationDateTime(LocalDateTime.now())
                    .build();

            accountRepository.save(newAccount);

            Status resultStatus = Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("Account has been added successfully.")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<AccountResponse>builder()
                    .data(AccountResponse.builder()
                            .accountId(newAccount.getAccountId())
                            .accountStatus(newAccount.getAccountStatus())
                            .accountType(newAccount.getAccountType())
                            .accountBalance(newAccount.getAccountBalance())
                            .creationDateTime(newAccount.getCreationDateTime())
                            .build())
                    .status(resultStatus)
                    .build();
        } else {
            Status resultStatus = Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .message("User Does Not Exists.")
                    .success(Boolean.TRUE)
                    .build();

            return WrapperResponse.<AccountResponse>builder()
                    .status(resultStatus)
                    .build();
        }
    }

    public List<Account> allAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    public List<Account> allAccountsByUserId(UUID userId) {
        User user= userRepository.findByUserId(userId);
        return accountRepository.findByUser(user);
    }
}
