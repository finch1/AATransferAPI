package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.AccountModel.Account;

import java.util.List;
import java.util.UUID;

public interface IAccountDAO {
    boolean insertNewAccountForUser(Account account);

    List<Account> getAllAccountsForUser(Integer userID);

    boolean updateAccountDetailsOfUser(UUID userID, Account new_account);

    boolean deleteAccountOfUser(UUID userID);
}
