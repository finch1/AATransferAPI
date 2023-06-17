package com.AATransferAPI.AATransferAPI.TransfersModel;

import com.AATransferAPI.AATransferAPI.AccountModel.AccountService;
import com.AATransferAPI.AATransferAPI.DAO.ITransfersDAO;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransfersService {

    private final ITransferRepository repository;

    @Autowired
    public TransfersService(@Qualifier("MySQLTransfers") ITransferRepository repository) {
        this.repository = repository;
    }

    public List<Transfers> getAllTransfers(){
        return repository.getAllTransfers();
    }

    public String MakeTransfer(Transfers transfers){
        return repository.MakeTransfer(transfers.get_accountFrom(), transfers.get_accountTo(), transfers.get_amount());
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
