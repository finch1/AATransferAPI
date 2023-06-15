package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.AccountModel.Account;

import java.util.UUID;

public interface IAccountDAO {

    int insertAccount(UUID accountID);
    default int addAccount(Account acc){
        UUID id = UUID.randomUUID();
        return  insertAccount(id);
    }
}
