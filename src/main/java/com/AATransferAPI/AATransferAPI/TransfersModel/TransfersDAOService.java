package com.AATransferAPI.AATransferAPI.TransfersModel;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class TransfersDAOService {

    List<Transfers> T_DB = new ArrayList<Transfers>();

    public List<Transfers> getTransfersForAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(transfers -> account.equals(transfers.get_accountFrom())).
                filter(transfers -> account.equals(transfers.get_accountTo())).
                findAny().
                orElse(null);

        return transfersList;
    }

    public List<Transfers> getTransfersFromAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(transfers -> account.equals(transfers.get_accountFrom())).
                findAny().
                orElse(null);

        return transfersList;
    }

    public List<Transfers> getTransfersToAccount(UUID account){

        List<Transfers> transfersList = T_DB.stream().
                filter(transfers -> account.equals(transfers.get_accountTo())).
                findAny().
                orElse(null);

        return transfersList;
    }

    public Enum MakeTransfer(Transfers transfers){

        // lock until transaction done
        return _transfersDAOService.AddDeposit();
    }

}
