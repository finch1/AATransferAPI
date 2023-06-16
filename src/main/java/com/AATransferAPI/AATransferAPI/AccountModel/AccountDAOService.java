package com.AATransferAPI.AATransferAPI.AccountModel;

import com.AATransferAPI.AATransferAPI.DAO.IAccountDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("MySQLAccounts")
public class AccountDAOService implements IAccountDAO {

    private  static List<Account> A_DB = new ArrayList<Account>();

    @Override
    public boolean insertNewAccountForUser(Account account){
        A_DB.add(account);
        return true;
    }

    @Override
    public List<Account> getAllAccountsForUser(Integer userID){

        List<Account> accountsList = A_DB.stream().
                filter(a -> a.get_userID().equals(userID)).
                collect(Collectors.toList());

        return accountsList;
    }

    @Override
    public boolean updateAccountDetailsOfUser(UUID userID, Account new_account){
        Account oldAccount = A_DB.stream().filter(a -> a.get_userID().equals(userID)).findFirst().orElse(null);

        if(oldAccount != null){
            oldAccount.set_status(new_account.get_status());
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteAccountOfUser(UUID userID){
        if(userID != null)
        {
            return A_DB.removeIf(account -> account.get_userID().equals(userID));
        }
        return false;
    }

}
