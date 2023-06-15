package com.AATransferAPI.AATransferAPI.AccountModel;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AccountDAOService {

    private  static List<Account> A_DB = new ArrayList<Account>();

    public boolean insertNewAccountForUser(Account account){
        A_DB.add(account);
        return true;
    }

    public List<Account> getAllAccountsForUser(UUID userID){

        List<Account> accountsList = A_DB.stream().
                filter(a -> a.get_userID().equals(userID)).
                collect(Collectors.toList());

        return accountsList;
    }

    public boolean updateAccountDetailsOfUser(UUID userID, Account new_account){
        Account oldAccount = A_DB.stream().filter(a -> a.get_userID().equals(userID)).findFirst().orElse(null);

        if(oldAccount != null){
            oldAccount.set_status(new_account.get_status());
            return true;
        }

        return false;
    }

    public boolean deleteAccountOfUser(UUID userID){
        if(userID != null)
        {
            return A_DB.removeIf(account -> account.get_userID().equals(userID));
        }
        return false;
    }

}
