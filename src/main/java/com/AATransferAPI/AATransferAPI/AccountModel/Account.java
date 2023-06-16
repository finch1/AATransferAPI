package com.AATransferAPI.AATransferAPI.AccountModel;

//:)import jakarta.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

//:)@Entity
public class Account implements IAccount {

    private AccountStatusEnum.Status _status;
    private UUID _accountID;
    @NotBlank(message = "Account holder ID is required.")
    private Integer _userID;
    private Double _balance;

    public Account(@JsonProperty("userID") Integer _userID) {
        this._accountID = UUID.randomUUID();
        this._userID = _userID;
        this._balance = 0.0;
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
