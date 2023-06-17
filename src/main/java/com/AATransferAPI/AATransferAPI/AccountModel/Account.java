package com.AATransferAPI.AATransferAPI.AccountModel;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

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
            strategy = GenerationType.SEQUENCE,
            generator = "account_seq"
    )
    private UUID _accountID;
    @NotBlank(message = "Account holder ID is required.")
    @Positive(message = "ID Greater then zero.")
    private Integer _userID;
    private Double _balance;
    private AccountStatusEnum.Status _status;

    public Account(@JsonProperty("userID") Integer _userID) {
        this._accountID = UUID.randomUUID();
        this._userID = _userID;
        this._balance = 100.0; // adding funds to test with
        this._status = AccountStatusEnum.Status.ACTIVE;
    }

    @Override
    public UUID get_accountID() {
        return _accountID;
    }

    @Override
    public void set_accountID(UUID _accountID) {
        this._accountID = _accountID;
    }

    @Override
    public Integer get_userID() {
        return _userID;
    }

    @Override
    public void set_userID(Integer _userID) {
        this._userID = _userID;
    }

    @Override
    public Double get_balance() {
        return _balance;
    }

    @Override
    public void set_balance(Double _balance) {
        this._balance = _balance;
    }

    public AccountStatusEnum.Status get_status() {
        return _status;
    }

    public void set_status(AccountStatusEnum.Status _status) {
        this._status = _status;
    }
}
