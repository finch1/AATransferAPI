package com.AATransferAPI.AATransferAPI.TransfersModel;

import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
@Qualifier("MySQLTransfers")
public interface ITransfersRepository extends JpaRepository<Transfers, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // not sure about this
    @Procedure(procedureName = "sp_MakeTransfer", outputParameterName = "message")
    String MakeTransfer(@Param("from_account") Long from_account, @Param("to_account") Long to_account, @Param("amount") Double amount);

    List<Transfers> findByaccountFrom(Long accountID);
    List<Transfers> findByaccountTo(Long accountID);

}
