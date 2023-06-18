package com.AATransferAPI.AATransferAPI.API;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import com.AATransferAPI.AATransferAPI.Service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Account> accountList = accountService.getAllAccounts();
        if (!accountList.isEmpty()) {
            map.put("status", 1);
            map.put("data", accountList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/user/{userID}")
    public ResponseEntity<?> getAllAccountsForUser(@PathVariable("userID")Long userID){

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Account> accountList = accountService.getAllAccountsForUser(userID);
        if (!accountList.isEmpty()) {
            map.put("status", 1);
            map.put("data", accountList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/account/{accountID}")
    public ResponseEntity<?> getAccount(@PathVariable("accountID")Long accountID){

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<Account> accountO = accountService.getAccount(accountID);
        if (!accountO.isEmpty()) {
            map.put("status", 1);
            map.put("data", accountO);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    // When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    // @RequestBody = convert JSON payload to java object
    // @Valid = checks the annotations in the object itself
    @PostMapping
    public ResponseEntity<?> insertNewAccountForUser(@Valid @RequestBody Account account){

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        if(accountService.insertNewAccountForUser(account).get_accountID() != null) {
            map.put("status", 1);
            map.put("message", "Record is Saved Successfully!");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        }else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Failed to create new account.");
            return new ResponseEntity<>(map, HttpStatus.NOT_IMPLEMENTED);
        }
    }
/*
    @PutMapping("/update/{accountID}{status}")
    public ResponseEntity<?> updateUserById(@PathVariable Long accountID, @Valid @RequestBody Account account) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Account oldAccount = accountService.findByAccountId(accountID);
            user.setUserName(userDetail.getUserName());
            user.setMobileNo(userDetail.getMobileNo());
            user.setEmailId(userDetail.getEmailId());
            user.setCity(userDetail.getCity());
            user.setPassword(userDetail.getPassword());
            userService.save(user);
            map.put("status", 1);
            map.put("data", userService.findById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }


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
