package com.AATransferAPI.AATransferAPI.AccountModel;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Account implements IAccount {


    private UUID _accountID;
    @NotBlank(message = "Account holder ID is required.")
    private Integer _userID;
    private Double _balance;

    public Account(Integer _userID) {
        this._accountID = UUID.randomUUID();
        this._userID = _userID;
        this._balance = 0.0;
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
}
