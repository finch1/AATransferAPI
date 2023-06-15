package com.AATransferAPI.AATransferAPI.TransfersModel;

import com.AATransferAPI.AATransferAPI.AccountModel.Account;
import com.AATransferAPI.AATransferAPI.AccountModel.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransfersService {

    private final TransfersDAOService _transfersDAOService;
    private final AccountService _accountService;

    @Autowired
    public TransfersService(TransfersDAOService transfersDAOService) {
        this._transfersDAOService = transfersDAOService;
    }

    public String getTransfersForAccount(UUID account){
        return  _transfersDAOService.getTransfersForAccount();
    }

    public String getTransfersFromAccount(UUID account){
        return  _transfersDAOService.getTransfersFromAccount();
    }

    public String getTransfersToAccount(UUID account){
        return  _transfersDAOService.getTransfersToAccount();
    }

    public Boolean MakeTransfer(Transfers transfer){

        UUID _fromAccount = transfer.get_accountFrom();
        UUID _toAccount = transfer.get_accountTo();

        List<Account> compareAccounts =  _accountDAOService.getAccountsForTransfer(_fromAccount, _toAccount);

        // do accounts exist
        if(compareAccounts.size() == 2) {
            // does from account have enough balance?
            _accountService.checkBalanceAndCurrency(_fromAccount, transfer.get_amount(), transfer.get_currency());
            // does from & to account have the currency
            _accountService.checkStatusAndCurrency(_toAccount, transfer.get_currency());

            // transfer money from account to account
            _transfersDAOService.MakePayment(transfer);
            }
            // send notification (handle event) https://www.google.com/search?client=firefox-b-d&q=c%23+events+in+java

        return _transfersDAOService.MakePayment();
    }

}
