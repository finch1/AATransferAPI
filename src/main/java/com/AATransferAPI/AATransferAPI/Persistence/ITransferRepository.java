package com.AATransferAPI.AATransferAPI.Persistence;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Repository
@Qualifier("MySQLTransfers")
public interface ITransferRepository extends JpaRepository<Transfer, UUID> {

    // @Lock(LockModeType.PESSIMISTIC_WRITE) // not sure about this
    @Procedure(procedureName = "sp_MakeTransfer", outputParameterName = "message")
    String makeTransfer(@Param("fromAccount") Long from_account,
                        @Param("toAccount") Long to_account,
                        @Param("amount") Double amount,
                        @Param("transferRef") String transferRef,
                        @Param("createdOn") LocalDateTime created_On);


    @Procedure(procedureName = "sp_TransferForUser")
    @Transactional(readOnly = true)
    List<Transfer> getTransfersForUser(@Param("userID") Long userID);


    @Procedure(procedureName = "sp_TransferForAccount")
    @Transactional(readOnly = true)
    List<Transfer> getTransfersForAccount(@Param("accountID") Long from_account);

}
