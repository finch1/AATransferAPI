package com.AATransferAPI.AATransferAPI.Service;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import com.AATransferAPI.AATransferAPI.Persistence.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TransferService {

    private final ITransferRepository repository;

    @Autowired
    public TransferService(@Qualifier("MySQLTransfers") ITransferRepository repository) {
        this.repository = repository;
    }

    public List<Transfer> getAllTransfers(){
        return repository.findAll();
    }

    public String MakeTransfer(Transfer transfer){
        return repository.MakeTransfer(transfer.get_accountFrom(), transfer.get_accountTo(), transfer.get_amount(), transfer.get_currency(), transfer.get_transferRef(), transfer.getCreatedOn());
    }

    /*
        public List<Transfers> getTransfersForUser(UUID userID){
        return  _transfersDAOService.getTransfersForUser(userID);
    }

    public List<Transfers> getTransfersFromAccount(UUID accountID){
        return  _transfersDAOService.getTransfersFromAccount(accountID);
    }

    public List<Transfers> getTransfersToAccount(UUID accountID){
        return  _transfersDAOService.getTransfersToAccount(accountID);
    }

     */

}
