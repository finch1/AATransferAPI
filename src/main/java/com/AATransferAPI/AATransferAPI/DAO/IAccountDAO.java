package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.AccountModel.Account;

import java.util.List;

public interface IAccountDAO {
    List<Account> getAllAccountsForUser(Long userID);

    boolean insertNewAccountForUser(Account account);

    /*
    Optional<Account> getAccountForUser(Integer userID, UUID accountID);

    boolean updateAccountDetailsOfUser(UUID userID, Account new_account);

    boolean deleteAllAccountsOfUser(UUID userID);
    boolean deleteAccountOfUser(UUID userID);
    */
}
