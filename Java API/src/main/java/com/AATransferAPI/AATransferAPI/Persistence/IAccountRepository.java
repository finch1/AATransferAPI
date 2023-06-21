package com.AATransferAPI.AATransferAPI.Persistence;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import com.AATransferAPI.AATransferAPI.ModelAccount.AccountStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MySQLAccount")
public interface IAccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUserID(Long _userID);
}
