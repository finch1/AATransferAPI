package com.AATransferAPI.AATransferAPI.Service;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import com.AATransferAPI.AATransferAPI.Persistence.IAccountRepository;
import com.AATransferAPI.AATransferAPI.Persistence.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
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

    public List<Transfer> getTransfersForUser(Long userID){
        return  repository.getTransfersForUser(userID);
    }

    public List<Transfer> getTransfersForAccount(Long accountID){
        return repository.getTransfersForAccount(accountID);
    }

    public String MakeTransfer(Transfer transfer){
        return repository.MakeTransfer(transfer.get_accountFrom(),
                                        transfer.get_accountTo(),
                                        transfer.get_amount(),
                                        transfer.get_transferRef(),
                                        LocalDateTime.now()); // not sure weather the now() has any repercussions
    }





}
