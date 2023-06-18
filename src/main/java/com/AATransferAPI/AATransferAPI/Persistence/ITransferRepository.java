package com.AATransferAPI.AATransferAPI.Persistence;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Repository
@Qualifier("MySQLTransfers")
public interface ITransferRepository extends JpaRepository<Transfer, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // not sure about this
    @Procedure(procedureName = "sp_MakeTransfer", outputParameterName = "message")
    String MakeTransfer(@Param("from_account") Long from_account,
                        @Param("to_account") Long to_account,
                        @Param("amount") Double amount,
                        @Param("transferRef") String transferRef,
                        @Param("created_On") LocalDateTime created_On);

    List<Transfer> findByaccountFrom(Long accountID);
    List<Transfer> findByaccountTo(Long accountID);

}
