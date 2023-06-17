package com.AATransferAPI.AATransferAPI.AccountModel;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
public class Account implements IAccount {

    @Id
    @SequenceGenerator(
            name = "account_seq",
            sequenceName = "account_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "account_seq"
    )
    private Long accountID;

    private Date accountDate;
    @NotBlank(message = "Account holder ID is required.")
    @Positive(message = "ID Greater then zero.")
    private Long userID;
    private Double balance;
    private AccountStatusEnum.Status status;
    @CreatedDate
    private LocalDateTime createDate;
    @NotBlank(message = "Account currency required.")
    private String currency;

    public Account(@JsonProperty("userID") Long userID,
                   @JsonProperty("currency") String currency) {
        this.userID = userID;
        this.balance = 100.0; // adding funds to test with
        this.status = AccountStatusEnum.Status.ACTIVE;
        this.currency = currency;
    }

    @Override
    public Long get_accountID() {
        return accountID;
    }

    @PrePersist
    @Override
    void get_accountDate() {
        this.accountDate = new Date();
    }

    @Override
    public Long get_userID() {
        return userID;
    }

    @Override
    public Double get_balance() {
        return balance;
    }

    @Override
    public void set_balance(Double balance) {
        this.balance = balance;
    }

    @Override
    public AccountStatusEnum.Status get_status() {
        return status;
    }

    @Override
    public void set_status(AccountStatusEnum.Status status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }
}
