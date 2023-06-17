package com.AATransferAPI.AATransferAPI.AccountModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final IAccountRepository repository;

    @Autowired
    public AccountService(@Qualifier("MySQLTransfers") IAccountRepository repository) {
        this.repository = repository;
    }

    public Optional<Account> getAllAccountsByID(Long accountID){
        return repository.findById(accountID);
    }

    public List<Account> getAllAccountsForUser(Long userID){
        return repository.findAllByUserID(userID);

    }

    public Account insertNewAccountForUser(Account account){
        return repository.save(account);
    }

    /*
    public Optional<Account> updateAccountDetailsOfUser(UUID userID, Account account){
        return _accountDAOService.updateAccountDetailsOfUser(userID, account);
    }

    public Optional<Account> deleteAllAccountsOfUser(UUID userID){
            return _accountDAOService.deleteAccountOfUser(userID);
    }
    */

}
