package com.AATransferAPI.AATransferAPI.ModelAccount;

public interface IAccount {
    Long get_accountID();
    Long get_userID();
    Double get_balance();
    void set_balance(Double balance);
    AccountStatus get_status();
    void set_status(AccountStatus status);
    String getCurrency();
    void setCurrency(String currency);
}
