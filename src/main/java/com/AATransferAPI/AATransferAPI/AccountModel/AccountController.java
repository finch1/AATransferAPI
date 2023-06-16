package com.AATransferAPI.AATransferAPI.AccountModel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(path="api/v1/account")
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String welcome(){
        return "Welcome!";
    }

    // request account for userID
    @GetMapping(path = "{userID}")
    public List<Account> getAllAccountsForUser(@PathVariable("userID")Integer userID){
        if(userID != null)
            return accountService.getAllAccountsForUser(userID);

        return null;
    }

    // request account for accountID

    // When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    @PostMapping
    public boolean insertNewAccountForUser(@RequestBody Account account){
        return accountService.insertNewAccountForUser(account);
        // return accountID
    }

    @PutMapping(path = "{userID}")
    public boolean updateAccountDetailsOfUser(@PathVariable("userID") UUID userID, @Valid @RequestBody Account account){
        if(userID != null)
            return accountService.updateAccountDetailsOfUser(userID, account);

        return false;
    }

    @DeleteMapping(path = "{userID}")
    public boolean deleteAccountOfUser(@PathVariable("userID") UUID userID){
        if(userID != null)
            return accountService.deleteAccountOfUser(userID);

        return false;
    }
}
