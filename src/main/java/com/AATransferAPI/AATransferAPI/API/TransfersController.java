package com.AATransferAPI.AATransferAPI.API;

import com.AATransferAPI.AATransferAPI.ModelAccount.Account;
import com.AATransferAPI.AATransferAPI.ModelTransfer.Transfer;
import com.AATransferAPI.AATransferAPI.Service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "api/v1/transfers")
public class TransfersController {

    private final TransferService transferService;

    @Autowired
    public TransfersController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTransfers(){
        List<Transfer> transfersList = transferService.getAllTransfers();
        return commonResponse(Optional.empty(), transfersList, HttpStatus.NO_CONTENT, "No data found.");
    }

    @GetMapping(path = "/account/{accountID}")
    public ResponseEntity<?> getTransfersForAccount(@PathVariable("accountID") Long accountID){
        List<Transfer> transfersList = transferService.getTransfersForAccount(accountID);
        return commonResponse(Optional.empty(), transfersList, HttpStatus.NO_CONTENT, "No data found.");
    }

    @GetMapping(path = "/user/{userID}")
    public ResponseEntity<?> getTransfersForUser(@PathVariable("userID") Long userID){
        List<Transfer> transfersList = transferService.getTransfersForUser(userID);
        return commonResponse(Optional.empty(), transfersList, HttpStatus.NO_CONTENT, "No data found.");
    }

    @PostMapping(path = "makePayment")
    public ResponseEntity<?> MakePayment(@Valid @RequestBody Transfer transfer){
        String message = transferService.MakeTransfer(transfer);

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        if(message.contains("")) {

            // build response
            map.put("status", 1);
            map.put("message", message);
            return ResponseEntity
                    .created(
                            linkTo(methodOn(TransfersController.class)
                                    .MakePayment(transfer)).toUri()).body(null);
        }else {
            map.clear();
            map.put("status", 0);
            map.put("message", message);
            return new ResponseEntity<>(map, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    // common logic to build response
    private ResponseEntity<?> commonResponse(Optional<Transfer> transfer, List<Transfer> listTransfer, HttpStatus failureStatus, String failureMessage) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        if (!transfer.isEmpty()) {
            // build response
            map.put("status", 1);
            map.put("data", transfer);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else if (!listTransfer.isEmpty()) {
            // build response
            map.put("status", 1);
            map.put("data", listTransfer);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", failureMessage);
            return new ResponseEntity<>(map, failureStatus);
        }
    }

}
