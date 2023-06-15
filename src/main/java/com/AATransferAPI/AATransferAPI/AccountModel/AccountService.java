package com.AATransferAPI.AATransferAPI.AccountModel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountDAOService _accountDAOService;

    @Autowired
    public AccountService(AccountDAOService _accountDAOService) {
        this._accountDAOService = _accountDAOService;
    }

    public List<Account> getAllAccountsForUser(UUID userID){
            return _accountDAOService.getAllAccountsForUser(userID);
    }

    public boolean insertNewAccountForUser( Account account){
        return _accountDAOService.insertNewAccountForUser(account);
    }

    public boolean updateAccountOfUser(UUID userID, Account account){
        return _accountDAOService.updateAccountOfUser(userID, account);
    }

    public boolean deleteAccountOfUser(UUID userID){
            return _accountDAOService.deleteAccountOfUser(userID);
    }

    // Check for enough account balance and currency
    public String checkBalanceAndStatusAndCurrency(Account account){

    }

    // Check for account currency
    public String checkStatusAndCurrency(Account account){

    }
}
