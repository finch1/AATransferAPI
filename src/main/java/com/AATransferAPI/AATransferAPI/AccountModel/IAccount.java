package com.AATransferAPI.AATransferAPI.AccountModel;

public interface IAccount {
    Long get_accountID();

    void get_accountDate();

    Long get_userID();


    Double get_balance();

    void set_balance(Double balance);

    AccountStatusEnum.Status get_status();

    void set_status(AccountStatusEnum.Status status);

    void currency(String currency);

    String currency();
}
