package com.AATransferAPI.AATransferAPI.ModelAccount;

import com.AATransferAPI.AATransferAPI.Audit.Audit;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "accounts",
        indexes = {@Index(name = "IX_accounts_user_ID",  columnList="userID", unique = false)})
public class Account implements IAccount{

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator( name = "native")
    private Long accountID;

    @Positive(message = "ID Greater then zero.")
    @Column(nullable = false, updatable = false)
    private Long userID;
    @Positive
    @Column(nullable = false)
    private Double balance;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;
    @NotBlank(message = "Account currency required.")
    @Column(nullable = false, updatable = false)
    private String currency;
    @Embedded
    private Audit audit = new Audit();

    public Account() {
    }

    public Account(@JsonProperty("userID") Long userID,
                   @JsonProperty("currency") String currency,
                   @JsonProperty("balance") Double balance) {
        this.userID = userID;
        this.balance = balance; // adding funds to test with
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
    public AccountStatus get_status() {
        return status;
    }

    @Override
    public void set_status(AccountStatus status) {
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
