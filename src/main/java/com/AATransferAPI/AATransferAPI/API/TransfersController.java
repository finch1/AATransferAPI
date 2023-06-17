package com.AATransferAPI.AATransferAPI.API;

import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import com.AATransferAPI.AATransferAPI.Service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transfers")
public class TransfersController {

    private final TransferService transferService;

    @Autowired
    public TransfersController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public List<Transfer> getAllTransfers(){
        return transferService.getAllTransfers();
    }

    @PostMapping(path = "makePayment")
    public String MakePayment(@Valid @RequestBody Transfer transfer){
        return transferService.MakeTransfer(transfer);
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
