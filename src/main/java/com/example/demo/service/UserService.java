package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.enums.StatusCode;
import com.example.demo.model.Status;
import com.example.demo.model.WrapperResponse;
import com.example.demo.repo.AccountRepository;
import com.example.demo.repo.UserRepository;
import com.example.demo.request.UserRequest;
import com.example.demo.response.UnifiedAccountResponse;
import com.example.demo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public WrapperResponse<UserResponse> addUser(UserRequest userRequest) {
        if(Objects.isNull(userRepository.findByUserContact(userRequest.getUserContact()))) {
            User newUser= User.builder()
                    .name(userRequest.getName())
                    .userContact(userRequest.getUserContact())
                    .userAddress(userRequest.getUserAddress())
                    .userEmail(userRequest.getUserEmail())
                    .userPassword(userRequest.getUserPassword())
                    .userName(userRequest.getUserName())
                    .build();

            Status resultStatus= Status.builder()
                    .code(StatusCode.SUCCESS.getCode())
                    .message("User has been added successfully.")
                    .success(Boolean.TRUE)
                    .build();

            userRepository.save(newUser);

            return WrapperResponse.<UserResponse>builder()
                    .data(UserResponse.builder()
                            .userId(newUser.getUserId())
                            .name(newUser.getName())
                            .userContact(newUser.getUserContact())
                            .userAddress(newUser.getUserAddress())
                            .userEmail(newUser.getUserEmail())
                            .userPassword(newUser.getUserPassword())
                            .userName(newUser.getUserName())
                            .build())
                    .status(resultStatus)
                    .build();
        } else {
            Status resultStatus= Status.builder()
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .success(Boolean.FALSE)
                    .message("User already exists")
                    .build();

            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        }
    }

    public User getUserByEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }


    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public WrapperResponse<UserResponse> allocateAccount(UUID userId, UUID accountId) {
        User user= userRepository.findByUserId(userId);
        Account account= accountRepository.findByAccountId(accountId);

        if(!Objects.isNull(user) && !Objects.isNull(account)) {
            user.getAccounts().add(account);
            account.setUser(user);
            userRepository.save(user);
            accountRepository.save(account);

            Status resultStatus= Status.builder()
                    .message("Account Allocated Successfully")
                    .success(Boolean.TRUE)
                    .code(StatusCode.SUCCESS.getCode())
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if ((Objects.isNull(user)) ||(Objects.isNull(user) && !Objects.isNull(account))) {
            Status resultStatus= Status.builder()
                    .message("User Is Invalid")
                    .success(Boolean.TRUE)
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else if ((Objects.isNull(account)) ||(Objects.isNull(account) && !Objects.isNull(user))) {
            Status resultStatus= Status.builder()
                    .message("Account Is Invalid")
                    .success(Boolean.TRUE)
                    .code(StatusCode.BAD_REQUEST.getCode())
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        } else {
            Status resultStatus= Status.builder()
                    .message("Something Went Wrong")
                    .success(Boolean.TRUE)
                    .code(StatusCode.INTERNAL_SERVER_ERROR.getCode())
                    .build();
            return WrapperResponse.<UserResponse>builder()
                    .status(resultStatus)
                    .build();
        }
    }

    public WrapperResponse<UnifiedAccountResponse> getUnifiedAccounts(UUID userId) {
        User user= userRepository.findByUserId(userId);
        List<Account> accounts=user.getAccounts();
        Double totalBalance=accounts.stream()
                .map(x -> x.getAccountBalance())
                .reduce((double) 0, (a, b) -> a + b);

        Status resultStatus= Status.builder()
                .message("Accounts Unified Successfully")
                .success(Boolean.TRUE)
                .code(StatusCode.SUCCESS.getCode())
                .build();

        return WrapperResponse.<UnifiedAccountResponse>builder()
                .data(UnifiedAccountResponse.builder()
                        .totalAccountBalance(totalBalance)
                        .totalAccounts(Integer.valueOf(accounts.size()))
                        .build())
                .status(resultStatus)
                .build();
    }

}
