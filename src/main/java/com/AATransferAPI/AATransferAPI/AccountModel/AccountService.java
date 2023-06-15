package com.AATransferAPI.AATransferAPI.AccountModel;

import com.AATransferAPI.AATransferAPI.TransfersModel.Transfers;
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

    public boolean verfiyAccountsForTransfer(Transfers transfer){
        UUID _fromAccount = transfer.get_accountFrom();
        UUID _toAccount = transfer.get_accountTo();

        // do accounts exist
        // does from account have enough balance?
        checkBalanceAndStatusAndCurrency(_fromAccount, transfer.get_amount(), transfer.get_currency());
        // does from & to account have the currency
        checkStatusAndCurrency(_toAccount, transfer.get_currency());

        return true
    }

    // Check for enough account balance and currency
    public String checkBalanceAndStatusAndCurrency(UUID account, double amount, Enum currency){

    }

    // Check for account currency
    public String checkStatusAndCurrency(UUID account, Enum currency){

    }
}
