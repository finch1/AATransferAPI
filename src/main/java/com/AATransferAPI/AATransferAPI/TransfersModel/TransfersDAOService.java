package com.AATransferAPI.AATransferAPI.TransfersModel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("MySQLTransfers")
public class TransfersDAOService implements com.AATransferAPI.AATransferAPI.DAO.ITransfersDAO {

    List<Transfers> T_DB = new ArrayList<>();

    @Override
    public List<Transfers> getTransfersForAccount(Long account){

        List<Transfers> transfersList = T_DB.stream().
                filter(t -> t.get_accountFrom().equals(account) || t.get_accountTo().equals(account)).
                collect(Collectors.toList());

        return transfersList;
    }

    @Override
    public TransfersStatusEnum.Status MakeTransfer(Transfers transfers){

        // lock until transaction done
        T_DB.add(transfers);

        return TransfersStatusEnum.Status.SUCCESSFUL;
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
