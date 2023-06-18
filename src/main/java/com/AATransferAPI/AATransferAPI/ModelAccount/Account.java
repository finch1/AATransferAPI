package com.AATransferAPI.AATransferAPI.ModelAccount;

import com.AATransferAPI.AATransferAPI.Audit.Audit;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "accounts")
public class Account implements IAccount{

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long accountID;
    //private Date accountDate;
    @Positive(message = "ID Greater then zero.")
    private Long userID;
    @Positive
    private Double balance;
    @NotNull
    private AccountStatusEnum.Status status;
    //@CreatedDate
    //private LocalDateTime createDate;
    @NotBlank(message = "Account currency required.")
    private String currency;

    @Embedded
    private Audit audit = new Audit();

    public Account() {
    }

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

    //@Override
    //void get_accountDate() {this.accountDate = new Date();}

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

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}