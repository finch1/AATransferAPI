package com.AATransferAPI.AATransferAPI.TransfersModel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "{userID}")
    public List<Transfers> getTransfersForUser(@PathVariable("userID") UUID userID){
        if(userID != null)
            return  transfersService.getTransfersForUser(userID);

        return null;
    }

    @GetMapping(path = "{accountID}")
    public List<Transfers> getTransfersForAccount(@PathVariable("accountID") UUID accountID){
        if(accountID != null)
            return  transfersService.getTransfersForAccount(accountID);

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

    @PostMapping(path = "makePayment")
    public TransfersStatusEnum.Status MakePayment(@Valid @RequestBody Transfers transfers){
        return transfersService.MakeTransfer(transfers);
    }
}
