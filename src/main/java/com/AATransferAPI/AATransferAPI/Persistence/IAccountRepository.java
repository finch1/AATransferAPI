package com.AATransferAPI.AATransferAPI.Persistence;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MySQLAccount")
public interface IAccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUserID(Long _userID);
}
