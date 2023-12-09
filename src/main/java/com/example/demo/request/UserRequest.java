package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.jetbrains.annotations.NotNull;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotNull
    private String name;

    @NotNull
    private String userContact;

    @NotNull
    private String userAddress;

    @NotNull
    private String userEmail;

    @NotNull
    private String userName;

    @NotNull
    private String userPassword;
}
