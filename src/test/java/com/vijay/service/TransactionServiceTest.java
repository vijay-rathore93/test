package com.vijay.service;

import com.vijay.entity.Transaction;
import com.vijay.jpa.TransactionRepo;
import com.vijay.modal.TransactionMO;
import com.vijay.modal.TransactionResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private TransactionService transactionService;






    @Test
    @Tag("This test is all about for checking transaction exists then update otherwise create")

    public void updateTransactionTest_create() {
        //List<Transaction> allTx = getAllTransactions();
        List<TransactionMO> allTxMO= getAllTransactionDTO();
        when(transactionRepo.findById(12345)).thenReturn(Optional.empty());
        Transaction transaction= Transaction.builder()
                .transactionId(12345)
                .amount(150.0)
                .currency("INR")
                .parentId(1)
                .type("Expense")
                .build();
        //when(transactionRepo.save(transaction)).thenReturn(any());
        TransactionResponse response = transactionService.updateTransaction(12345,allTxMO.get(4));
        assertNotNull(response);
        assertEquals("Ok", response.getStatus());
    }


    @Test
    @Tag("This test is all about for checking transaction exists then update otherwise create")
    public void updateTransactionTest_update() {
        List<TransactionMO> allTxMO= getAllTransactionDTO();
        Transaction transaction= Transaction.builder()
                .transactionId(12345)
                .amount(150.0)
                .currency("INR")
                .parentId(1)
                .type("Expense")
                .build();
        when(transactionRepo.findById(12345)).thenReturn(Optional.of(transaction));
        TransactionResponse response = transactionService.updateTransaction(12345,allTxMO.get(4));
        assertNotNull(response);
        assertEquals("Ok", response.getStatus());
    }
















    @Test
    @Tag("Get all transaction corresponding to provided type of transaction")
    public void getAllTransaction_BasedOnType() {
        String expense="Expense";
        List<Transaction> allTx = getAllTransactions();
        when(transactionRepo.findByType(expense)).thenReturn(Optional.of(allTx));
        List<Integer> txIds = transactionService.getInfo(expense);
        assertNotNull(txIds);
        assertEquals(3, txIds.size());
    }

    @Test
    @Tag("Get all transaction currencies")
    public void getAllCurrencies() {
        List<Transaction> allTx = getAllTransactions();
        when(transactionRepo.findAll()).thenReturn(allTx);
        Set<String> currencies = transactionService.getCurrencies();
        assertNotNull(currencies);
        assertEquals(2, currencies.size());
    }

    @Test
    @Tag("Get sum of all amount")
    public void getSum() {
        List<Transaction> allTx = getAllTransactions();
        when(transactionRepo.findAll()).thenReturn(allTx);
        Map<String,Double > response = transactionService.getSum(1234);
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(28000, response.get("USD"));
    }


    private List<TransactionMO> getAllTransactionDTO() {
        return new ArrayList<>(
                Arrays.asList(
                        TransactionMO.builder()
                                .amount(10000.0)
                                .currency("EUR")
                                .parentId(1)
                                .type("Expense")
                                .build(),
                        TransactionMO.builder()
                                .amount(13000.0)
                                .currency("USD")
                                .parentId(1)
                                .type("Expense")
                                .build(),
                        TransactionMO.builder()
                                .amount(15000.0)
                                .currency("USD")
                                .parentId(1)
                                .type("Expense")
                                .build(),
                        TransactionMO.builder()
                                .transactionId(123456)
                                .amount(15000.0)
                                .currency("USD")
                                .parentId(1)
                                .type("Expense")
                                .build()

                        ,TransactionMO.builder()
                                .transactionId(12345)
                                .amount(150.0)
                                .currency("INR")
                                .parentId(1)
                                .type("Expense")
                                .build()
                )
        );
    }

    private List<Transaction> getAllTransactions() {
        return new ArrayList<>(
                Arrays.asList(
                        Transaction.builder()
                                .amount(10000.0)
                                .currency("EUR")
                                .parentId(1)
                                .type("Expense")
                                .build(),
                        Transaction.builder()
                                .amount(13000.0)
                                .currency("USD")
                                .parentId(1)
                                .type("Expense")
                                .build(),
                        Transaction.builder()
                                .amount(15000.0)
                                .currency("USD")
                                .parentId(1)
                                .type("Expense")
                                .build()
                )
        );
    }
}
