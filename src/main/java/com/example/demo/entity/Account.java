package com.example.demo.entity;

import com.example.demo.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_details")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID accountId;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_balance")
    private double accountBalance;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @JsonIgnore
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

}
