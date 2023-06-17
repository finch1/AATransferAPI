package com.AATransferAPI.AATransferAPI.AccountModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("MySQLAccount")
public interface IAccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByUserID(Long _userID);
}
