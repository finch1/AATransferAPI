package com.AATransferAPI.AATransferAPI.Service;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import com.AATransferAPI.AATransferAPI.ModelAccount.AccountStatus;
import com.AATransferAPI.AATransferAPI.Persistence.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final IAccountRepository repository;

    @Autowired
    public AccountService(@Qualifier("MySQLAccount") IAccountRepository repository) {
        this.repository = repository;
    }

    // Methods
    public List<Account> getAllAccounts() {return repository.findAll(); };

    public List<Account> getAllAccountsForUser(Long userID){
        return repository.findAllByUserID(userID);
    }

    public Optional<Account> getAccountByID(Long accountID){
        return repository.findById(accountID);
    }

    public Account insertNewAccountForUser(Account account){
        return repository.save(account);
    }

    public Optional<Account> updateAccountStatus(Long accountID, AccountStatus status){
        // update query did not work on enum.
        Optional<Account> accountToUpdate = repository.findById(accountID);
        accountToUpdate.get().set_status(status);
        repository.saveAndFlush(accountToUpdate.get());
        return accountToUpdate;
    }

}
