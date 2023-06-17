package com.AATransferAPI.AATransferAPI.TransfersModel;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.List;

public class TransferRepository implements ITransfersRepository{

    private final ITransfersRepository repository;

    @Autowired
    public TransferRepository( ITransfersRepository repository) {
        this.repository = repository;
    }

    public List<Transfers> getAllTransfers(){
        return  repository.findAll();
    }

//        // do accounts exist
//        if(_accountService.verifyAccountsForTransfer(transfer)){
//            // transfer money from account to account
//            _transfersDAOService.MakeTransfer(transfer);
//            // update account balance
//            _accountService.updateAccountsAfterTransfer(transfer);
//            }
//            // send notification (handle event) https://www.google.com/search?client=firefox-b-d&q=c%23+events+in+java

        //return TransfersStatusEnum.Status.SUCCESSFUL;
    }

}
