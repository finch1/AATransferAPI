package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.TransfersModel.Transfers;
import com.AATransferAPI.AATransferAPI.TransfersModel.TransfersStatusEnum;

import java.util.List;
import java.util.UUID;

public interface ITransfersDAO {


    List<Transfers> getTransfersForAccount(UUID account);
    TransfersStatusEnum.Status MakeTransfer(Transfers transfers);
    /*
    List<Transfers> getTransfersForUser(UUID userID);

    List<Transfers> getTransfersFromAccount(UUID account);

    List<Transfers> getTransfersToAccount(UUID account);
*/
}
