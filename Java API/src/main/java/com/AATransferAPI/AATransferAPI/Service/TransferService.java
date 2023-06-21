package com.AATransferAPI.AATransferAPI.Service;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
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

    public List<Transfer> getTransfersForAccount(Long accountID){
        return repository.getTransfersForAccount(accountID);
    }

    public List<Transfer> getTransfersForUser(Long userID){
        return repository.getTransfersForUser(userID);
    }

    public String makeTransfer(Transfer transfer){
        return repository.makeTransfer(transfer.get_accountFrom(),
                                        transfer.get_accountTo(),
                                        transfer.get_amount(),
                                        transfer.get_transferRef(),
                                        transfer.getCreatedOn()); // not sure weather the now() has any repercussions
    }
}
