package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.AccountModel.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAccountDAO {
    List<Account> getAllAccountsForUser(Integer userID);

    boolean insertNewAccountForUser(Account account);

    /*
    Optional<Account> getAccountForUser(Integer userID, UUID accountID);

    boolean updateAccountDetailsOfUser(UUID userID, Account new_account);

    boolean deleteAllAccountsOfUser(UUID userID);
    boolean deleteAccountOfUser(UUID userID);
    */
}
