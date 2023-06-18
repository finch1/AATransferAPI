package com.AATransferAPI.AATransferAPI.Persistence;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import com.AATransferAPI.AATransferAPI.ModelAccount.AccountStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MySQLAccount")
public interface IAccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUserID(Long _userID);

    @Query("update accounts a set a.status = ?1 where a.accountid = ?2")
    @Modifying
    void updateStatus(Long accountID, AccountStatusEnum.Status status);
}
