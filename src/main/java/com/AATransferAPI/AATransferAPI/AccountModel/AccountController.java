package com.AATransferAPI.AATransferAPI.AccountModel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "{userID")
    public List<Account> getAllAccountsForUser(@PathVariable("userID")UUID userID){
        if(userID != null)
            return accountService.getAllAccountsForUser(userID);
    }

    // When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    @PostMapping
    public boolean insertNewAccountForUser(@Valid @RequestBody Account account){
        return accountService.insertNewAccountForUser(account);
    }

    @PutMapping(path = "{userID}")
    public boolean updateAccountOfUser(@PathVariable("userID") UUID userID, @RequestBody Account account){
        return accountService.updateAccountOfUser(userID, account);
    }

    @DeleteMapping(path = "{userID}")
    public boolean deleteAccountOfUser(@PathVariable("userID") UUID userID){
        if(userID != null)
            return accountService.deleteAccountOfUser(userID);
    }
}
