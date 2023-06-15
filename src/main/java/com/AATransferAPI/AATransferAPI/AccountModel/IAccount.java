package com.AATransferAPI.AATransferAPI.AccountModel;

import java.util.UUID;

public interface IAccount {
    UUID get_accountID();

    void set_accountID(UUID _accountID);

    Integer get_userID();

    void set_userID(Integer _userID);

    Double get_balance();

    void set_balance(Double _balance);
}
