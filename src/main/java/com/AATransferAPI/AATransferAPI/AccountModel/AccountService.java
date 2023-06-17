package com.AATransferAPI.AATransferAPI.AccountModel;

import com.AATransferAPI.AATransferAPI.DAO.IAccountDAO;
import com.AATransferAPI.AATransferAPI.TransfersModel.Transfers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final IAccountRepository iAccountRepository;

    @Autowired
    public AccountService(IAccountRepository iAccountRepository) {
        this.iAccountRepository = iAccountRepository;
    }

    @Autowired
    public AccountService(@Qualifier("MySQLAccounts") IAccountDAO _accountDAOService) {
        this._accountDAOService = _accountDAOService;
    }

    public List<Account> getAllAccountsForUser(Integer userID){
            return _accountDAOService.getAllAccountsForUser(userID);
    }

    public boolean insertNewAccountForUser( Account account){
        return _accountDAOService.insertNewAccountForUser(account);
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
