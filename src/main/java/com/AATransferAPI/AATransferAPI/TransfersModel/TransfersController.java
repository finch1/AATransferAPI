package com.AATransferAPI.AATransferAPI.TransfersModel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/transfers")
public class TransfersController {

    private final TransfersService transfersService;

    @Autowired
    public TransfersController(TransfersService transfersService) {
        this.transfersService = transfersService;
    }

    @GetMapping(path = "{accountID}")
    public String getTransfersForAccount(@PathVariable("accountID") UUID accountID){
        if(accountID != null)
            return  transfersService.getTransfersForAccount(accountID);
    }

    @GetMapping(path = "{accountID}")
    public String getTransfersFromAccount(@PathVariable("accountID") UUID accountID){
        if(accountID != null)
            return  transfersService.getTransfersForAccount(accountID);
    }

    @GetMapping(path = "{accountID}")
    public String getTransfersToAccount(@PathVariable("accountID") UUID accountID){
        if(accountID != null)
            return  transfersService.getTransfersToAccount(accountID);
    }

    @PostMapping(path = "makePayment")
    public Enum MakePayment(@Valid @RequestBody Transfers transfers){
        return transfersService.MakePayment(transfers);
    }
}
