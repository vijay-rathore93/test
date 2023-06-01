package com.vijay.controller;


import com.vijay.modal.TransactionMO;
import com.vijay.modal.TransactionResponse;
import com.vijay.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookingservice/")
public class TransactionController {

    private final TransactionService transactionService;

    @PutMapping("transaction/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable("transactionId") Integer transactionId,
                                                                 @RequestBody TransactionMO transactionMO){
        return new ResponseEntity<>(transactionService.updateTransaction(transactionId,transactionMO), HttpStatus.OK);
    }

    @GetMapping("types/{type}")
    public ResponseEntity<List<Integer>> getType(@PathVariable("type") String type){
        return new ResponseEntity<>(transactionService.getInfo(type), HttpStatus.OK);
    }

    @GetMapping("currencies")
    public ResponseEntity<Set<String>> getCurrencies(){
        return new ResponseEntity<>(transactionService.getCurrencies(), HttpStatus.OK);
    }

    @GetMapping("sum/{transactionId}")
    public ResponseEntity<Map<String,Double>> getSum(@PathVariable("transactionId") Integer transactionId){
        return new ResponseEntity<>(transactionService.getSum(transactionId), HttpStatus.OK);
    }
}
