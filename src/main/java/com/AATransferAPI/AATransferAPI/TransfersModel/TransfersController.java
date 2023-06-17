package com.AATransferAPI.AATransferAPI.TransfersModel;

import com.AATransferAPI.AATransferAPI.AccountModel.Account;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/transfers")
public class TransfersController {

    private final TransfersService transfersService;

    @Autowired
    public TransfersController(TransfersService transfersService) {
        this.transfersService = transfersService;
    }

    @GetMapping
    public List<Transfers> getAllTransfers(){
        return transfersService.getAllTransfers();
    }

    @PostMapping(path = "makePayment")
    public String MakePayment(@Valid @RequestBody Transfers transfers){
        return transfersService.MakeTransfer(transfers);
    }

/*
    @GetMapping(path = "{accountID}")
    public ResponseEntity<List<Transfers>> getTransfersForAccount(@PathVariable("accountID") Long accountID){

        List<Transfers> transfers = transfersService.getTransfersForAccount(accountID);

        if(transfers.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    @GetMapping(path = "{userID}")
    public List<Transfers> getTransfersForUser(@PathVariable("userID") UUID userID){
        if(userID != null)
            return  transfersService.getTransfersForUser(userID);

        return null;
    }

    @GetMapping(path = "/from/{accountID}")
    public List<Transfers> getTransfersFromAccount(@PathVariable("accountID") UUID accountID){
        if(accountID != null)
            return  transfersService.getTransfersFromAccount(accountID);

        return null;
    }

    @GetMapping(path = "/to/{accountID}")
    public List<Transfers> getTransfersToAccount(@PathVariable("accountID") UUID accountID){
        if(accountID != null)
            return  transfersService.getTransfersToAccount(accountID);

        return null;
    }

 */
}
