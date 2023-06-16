package com.AATransferAPI.AATransferAPI.AccountModel;

import com.AATransferAPI.AATransferAPI.DAO.IAccountDAO;
import com.AATransferAPI.AATransferAPI.TransfersModel.Transfers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final IAccountDAO _accountDAOService;

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

    public boolean updateAccountDetailsOfUser(UUID userID, Account account){
        return _accountDAOService.updateAccountDetailsOfUser(userID, account);
    }

    public boolean deleteAccountOfUser(UUID userID){
            return _accountDAOService.deleteAccountOfUser(userID);
    }

}
