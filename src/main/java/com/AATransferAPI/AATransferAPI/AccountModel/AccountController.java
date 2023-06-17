package com.AATransferAPI.AATransferAPI.AccountModel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<String>("Welcome", HttpStatus.OK);
    }

    @GetMapping(path = "{userID}")
    public ResponseEntity<List<Account>> getAllAccountsForUser(@PathVariable("userID")Integer userID){

        List<Account> account = accountService.getAllAccountsForUser(userID);

        if(account.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(account, HttpStatus.OK);
    }


    // When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    // @RequestBody = convert JSON payload to java object
    // @Valid = checks the annotations in the object itself
    @PostMapping
    public ResponseEntity<String> insertNewAccountForUser(@RequestBody Account account){ //@Valid @NotNull

        if(accountService.insertNewAccountForUser(account))
            return new ResponseEntity<>("New account created.", HttpStatus.OK);

        return new ResponseEntity<>("Failed to create new account.", HttpStatus.NOT_IMPLEMENTED);
    }

    /*
    @PutMapping(path = "{userID}")
    public ResponseEntity<String> updateAccountDetailsOfUser(@PathVariable("userID") UUID userID, @Valid @RequestBody Account account){

        Optional<Account> upd_account = accountService.updateAccountDetailsOfUser(userID, account);

        if(upd_account.isEmpty())
            return new ResponseEntity<>("Failed to update account.", HttpStatus.NOT_IMPLEMENTED);

        return new ResponseEntity<>("Account updated." + upd_account.get().get_accountID().toString(), HttpStatus.OK);

    }

    @DeleteMapping(path = "{userID}")
    public ResponseEntity<String> deleteAllAccountsOfUser(@PathVariable("userID") UUID userID){

        Optional<Account> del_account = accountService.deleteAllAccountsOfUser(userID);

        if(del_account.isEmpty())
            return new ResponseEntity<>("Failed to delete account/s.", HttpStatus.NOT_IMPLEMENTED);

        return new ResponseEntity<>("Account/s deleted." + del_account.get().get_accountID().toString(), HttpStatus.OK);
    }
        // TODO request account for accountID
        // TODO delete one account by userID and accountID
     */


}
