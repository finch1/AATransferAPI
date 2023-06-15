package com.AATransferAPI.AATransferAPI.TransfersModel;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransfersDAOService {

    List<Transfers> T_DB = new ArrayList<>();

    public List<Transfers> getTransfersForAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(t -> t.get_accountFrom().equals(account) || t.get_accountTo().equals(account)).
                collect(Collectors.toList());

        return transfersList;
    }

    public List<Transfers> getTransfersFromAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(t -> t.get_accountFrom().equals(account)).
                collect(Collectors.toList());

        return transfersList;
    }

    public List<Transfers> getTransfersToAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(t -> t.get_accountTo().equals(account)).
                collect(Collectors.toList());

        return transfersList;
    }

    public TransfersStatusEnum.Status MakeTransfer(Transfers transfers){

        // lock until transaction done
        T_DB.add(transfers);

        return TransfersStatusEnum.Status.SUCCESSFUL;
    }

}
