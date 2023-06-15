package com.AATransferAPI.AATransferAPI.AccountModel;

import com.AATransferAPI.AATransferAPI.DAO.IAccountDAO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountDAOService {

    private  static List<Account> A_DB = new ArrayList<Account>();

    public boolean insertNewAccountForUser(Account account){
        A_DB.add(account);
        return true;
    }

    public List<Account> getAllAccountsForUser(@PathVariable("userID") UUID userID){
        return accountService.getAllAccountsForUser(userID);
    }

    public boolean updateAccountOfUser(@PathVariable("userID") UUID userID, @RequestBody Account account){
        return accountService.updateAccountOfUser(userID, account);
    }

    public boolean deleteAccountOfUser(@PathVariable("userID") UUID userID){
        return accountService.deleteAccountOfUser(userID);
    }

    public List<Account> getAccountsForTransfer(Account fromAccount, Account toAccount){

    }

}
