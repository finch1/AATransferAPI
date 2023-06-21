package com.AATransferAPI.AATransferAPI.API;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import com.AATransferAPI.AATransferAPI.ModelAccount.AccountModelAssembler;
import com.AATransferAPI.AATransferAPI.ModelAccount.AccountStatus;
import com.AATransferAPI.AATransferAPI.Service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountModelAssembler assembler;

    @Autowired
    public AccountController(AccountService accountService, AccountModelAssembler assembler) {
        this.accountService = accountService;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        List<Account> accountList = accountService.getAllAccounts();
        return commonResponse(Optional.empty(), accountList, HttpStatus.NO_CONTENT, "No data found.");
    }

    @GetMapping(path = "/account/{accountID}")
    public ResponseEntity<?> getAccountByID(@PathVariable("accountID")Long accountID){
        Optional<Account> account = accountService.getAccountByID(accountID);
        return commonResponse(account, Collections.emptyList(), HttpStatus.NOT_FOUND, "No data found.");
    }

    @GetMapping(path = "/user/{userID}")
    public ResponseEntity<?> getAllAccountsForUser(@PathVariable("userID")Long userID){
        List<Account> accountList = accountService.getAllAccountsForUser(userID);
        return commonResponse(Optional.empty(), accountList, HttpStatus.NOT_FOUND, "No data found.");
    }

    // When the target argument fails to pass the validation, Spring Boot throws a MethodArgumentNotValidException exception.
    // @RequestBody = convert JSON payload to java object
    // @Valid = checks the annotations in the object itself
    @PostMapping(path = "/save")
    public ResponseEntity<?> insertNewAccountForUser(@Valid @RequestBody Account account){

        Account savedAccount = accountService.insertNewAccountForUser(account);

        if(savedAccount.get_accountID() != null) {

            EntityModel<Account> entityModel = assembler.toModel(savedAccount);

            return ResponseEntity
                    .created(
                            linkTo(methodOn(AccountController.class)
                                    .insertNewAccountForUser(savedAccount)).toUri()).body(entityModel);
        }else {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("status", 0);
            map.put("message", "Failed to create new account.");
            return new ResponseEntity<>(map, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    // Partial update, status only
    @PatchMapping(path = "/account/close")
    public ResponseEntity<?> closeAccount(@RequestParam("id") Long accountID){
        return updateAccountStatus(accountID, AccountStatus.CLOSED);
    }
    @PatchMapping(path = "/account/block")
    public ResponseEntity<?> blockAccount(@RequestParam("id") Long accountID){
        return updateAccountStatus(accountID, AccountStatus.BLOCKED);
    }
    @PatchMapping(path = "/account/reactivate")
    public ResponseEntity<?> reactivateAccount(@RequestParam("id") Long accountID){
        return updateAccountStatus(accountID, AccountStatus.ACTIVE);
    }

    // common logic to update account status
    private ResponseEntity<?> updateAccountStatus(Long accountID, AccountStatus statusEnum){
        Optional<Account> account = accountService.updateAccountStatus(accountID, statusEnum);
        return commonResponse(account, Collections.emptyList(), HttpStatus.NOT_IMPLEMENTED, "Account not updated.");
    }

    // common logic to build response
    private ResponseEntity<?> commonResponse(Optional<Account> account, List<Account> listAccounts, HttpStatus failureStatus, String failureMessage) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        if (!account.isEmpty()) {
            EntityModel<Account> entityModel = assembler.toModel(account.get());
            // build response
            map.put("status", 1);
            map.put("data", entityModel);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else if (!listAccounts.isEmpty()) {
            // add links for each account
            List<EntityModel<Account>> listEMAccount = listAccounts.stream().map(assembler::toModel).collect(Collectors.toList());

            // add links for response
            CollectionModel<EntityModel<Account>> collectionModel = CollectionModel.of(listEMAccount);
            collectionModel.add(linkTo(methodOn(AccountController.class).getAllAccounts()).withSelfRel());

            // build response
            map.put("status", 1);
            map.put("data", collectionModel);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", failureMessage);
            return new ResponseEntity<>(map, failureStatus);
        }
    }
}
