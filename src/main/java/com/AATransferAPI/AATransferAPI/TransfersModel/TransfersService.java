package com.AATransferAPI.AATransferAPI.TransfersModel;

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
    public TransfersService(TransfersDAOService _transfersDAOService, AccountService _accountService) {
        this._transfersDAOService = _transfersDAOService;
        this._accountService = _accountService;
    }

    public List<Transfers> getTransfersForAccount(UUID accountID){
        return  _transfersDAOService.getTransfersForAccount(accountID);
    }

    public List<Transfers> getTransfersFromAccount(UUID accountID){
        return  _transfersDAOService.getTransfersFromAccount(accountID);
    }

    public List<Transfers> getTransfersToAccount(UUID accountID){
        return  _transfersDAOService.getTransfersToAccount(accountID);
    }

    public TransfersStatusEnum.Status MakeTransfer(Transfers transfer){

        // do accounts exist
        if(_accountService.verifyAccountsForTransfer(transfer)){
            // transfer money from account to account
            _transfersDAOService.MakeTransfer(transfer);
            // update account balance
            _accountService.updateAccountsAfterTransfer(transfer);
            }
            // send notification (handle event) https://www.google.com/search?client=firefox-b-d&q=c%23+events+in+java

        return TransfersStatusEnum.Status.SUCCESSFUL;
    }

}
