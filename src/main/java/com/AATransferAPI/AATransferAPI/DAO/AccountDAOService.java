package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("MySQLAccounts")
public class AccountDAOService implements IAccountDAO {

    private List<Account> A_DB = new ArrayList<Account>();

    @Override
    public List<Account> getAllAccountsForUser(Long userID){
        return A_DB.stream().filter(a -> a.get_userID().equals(userID)).collect(Collectors.toList());
    }

    @Override
    public boolean insertNewAccountForUser(Account account){
        return A_DB.add(account);
    }

    /*

    @Override
    public Optional<Account> getAccountForUser(Integer userID, UUID accountID) {
        return A_DB.stream()
                .filter(a -> a.get_userID().equals(userID) && a.get_accountID().equals(accountID))
                .findFirst();
    }

    @Override
    public Optional<Account> updateAccountDetailsOfUser(Integer userID, UUID old_accountID, Account new_account){
        // 48:53 https://www.youtube.com/watch?v=vtPkZShrvXQ
        Optional<Account> oldAccount = getAccountForUser(userID, old_accountID);

        if(!oldAccount.isEmpty()){
            oldAccount.get().set_status(new_account.get_status());
            return oldAccount;
        }

        return null;
    }


    @Override
    public Optional<Account> deleteAllAccountsOfUser(Integer userID) {
        Optional<Account> accounts = getAllAccountsForUser(userID);

        if(accounts.isEmpty())
            return false;

        A_DB.remove(accounts.get());
        return true;
    }

    @Override
    public Optional<Account> deleteAccountOfUser(Integer userID, UUID accountID){

        Optional<Account> Account = getAccountForUser(userID, accountID);

        if(Account.isEmpty())
            return false;

        A_DB.remove(Account.get());
        return true;
    }
    */


}
