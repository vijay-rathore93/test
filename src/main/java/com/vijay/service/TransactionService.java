package com.vijay.service;

import com.vijay.entity.Transaction;
import com.vijay.jpa.TransactionRepo;
import com.vijay.modal.TransactionMO;
import com.vijay.modal.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;


    public TransactionResponse updateTransaction(Integer transactionId, TransactionMO transactionMO) {
        Transaction transaction = null;
        TransactionResponse transactionResponse=null;
        try {
            Optional<Transaction> optionalTransaction = transactionRepo.findById(transactionId);
            if (optionalTransaction.isPresent()) {
                transaction = optionalTransaction.get();
                if (null != transactionMO.getAmount()) {
                    transaction.setAmount(transactionMO.getAmount());
                }
                if (null != transactionMO.getAmount()) {
                    transaction.setCurrency(transactionMO.getCurrency());
                }
                if (null != transactionMO.getAmount()) {
                    transaction.setType(transactionMO.getType());
                }
                if (null != transactionMO.getAmount()) {
                    transaction.setParentId(transactionMO.getParentId());
                }

            } else {
                transaction = new Transaction();
                transaction.setAmount(transactionMO.getAmount());
                transaction.setCurrency(transactionMO.getCurrency());
                transaction.setType(transactionMO.getType());
                transaction.setParentId(transactionMO.getParentId());
            }

            if (null != transaction) {
                transactionRepo.save(transaction);
            }
            transactionResponse= TransactionResponse.builder().status("Ok").build();
        }catch(RuntimeException exception ){
            log.error("Error in saving or updating message:{}",exception.getLocalizedMessage());
            transactionResponse= TransactionResponse.builder().status("Failed").build();
        }
        return transactionResponse;
    }

    public List<Integer> getInfo(String type) {
        List<Integer> response=new ArrayList<>();
        Optional<List<Transaction>> optionalTransaction=transactionRepo.findByType(type);
        if(optionalTransaction.isPresent()){
            response.addAll(optionalTransaction.get().stream().map(Transaction::getTransactionId).collect(Collectors.toList()));
        }
        return response;

    }

    public List<String> getCurrencies() {
        return transactionRepo.findAll().stream().map(Transaction::getCurrency).collect(Collectors.toList());
    }

    public Map<String,Double > getSum(Integer transactionId) {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency,
                Collectors.summingDouble(obj->obj.getAmount())));
    }
}
