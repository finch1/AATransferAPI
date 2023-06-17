package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import com.AATransferAPI.AATransferAPI.ModelTransfer.TransferStatusEnum;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("MySQLTransfers")
public class TransfersDAOService implements com.AATransferAPI.AATransferAPI.DAO.ITransfersDAO {

    List<Transfer> T_DB = new ArrayList<>();

    @Override
    public List<Transfer> getTransfersForAccount(Long account){

        List<Transfer> transferList = T_DB.stream().
                filter(t -> t.get_accountFrom().equals(account) || t.get_accountTo().equals(account)).
                collect(Collectors.toList());

        return transferList;
    }

    @Override
    public TransferStatusEnum.Status MakeTransfer(Transfer transfer){

        // lock until transaction done
        T_DB.add(transfer);

        return TransferStatusEnum.Status.SUCCESSFUL;
    }

    /*
    @Override
        public List<Transfers> getTransfersForUser(UUID userID){

        return null;
    }



    @Override
    public List<Transfers> getTransfersFromAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(t -> t.get_accountFrom().equals(account)).
                collect(Collectors.toList());

        return transfersList;
    }

    @Override
    public List<Transfers> getTransfersToAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(t -> t.get_accountTo().equals(account)).
                collect(Collectors.toList());

        return transfersList;
    }
     */

}
