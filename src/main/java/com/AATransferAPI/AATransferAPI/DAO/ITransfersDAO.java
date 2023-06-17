package com.AATransferAPI.AATransferAPI.DAO;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import com.AATransferAPI.AATransferAPI.ModelTransfer.TransferStatusEnum;

import java.util.List;

public interface ITransfersDAO {


    List<Transfer> getTransfersForAccount(Long account);
    TransferStatusEnum.Status MakeTransfer(Transfer transfer);
    /*
    List<Transfers> getTransfersForUser(UUID userID);

    List<Transfers> getTransfersFromAccount(UUID account);

    List<Transfers> getTransfersToAccount(UUID account);
*/
}
