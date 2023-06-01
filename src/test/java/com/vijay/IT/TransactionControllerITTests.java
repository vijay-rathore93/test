package com.vijay.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.TransactionApplication;
import com.vijay.modal.TransactionMO;
import com.vijay.modal.TransactionResponse;
import com.vijay.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TransactionApplication.class
)
@AutoConfigureMockMvc
public class TransactionControllerITTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @Test
    @Tag("Saving transactionInfo")
    public void saveTransaction() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();

        MvcResult mvcResult =   mockMvc.perform(put("/bookingservice/transaction/12345").content(
                                objectMapper.writeValueAsString(getAllTransactionDTO().get(0))
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        TransactionResponse apiResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TransactionResponse.class);
        Assertions.assertNotNull(apiResponse);
        Assertions.assertEquals("Ok",apiResponse.getStatus());
    }

    @Test
    @Tag("Getting all currencies")
    public void getAllCurrencies() throws Exception {
        mockMvc.perform(get("/bookingservice/currencies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Tag("Getting all curencies")
    public void resendVerificationLink() throws Exception {
        MvcResult mvcResult =   mockMvc.perform(get("/bookingservice/types/expense")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()) .andReturn();;
    }

    @Test
    @Tag("Getting Sum for transaction")
    public void getSum() throws Exception {
        MvcResult mvcResult =  mockMvc.perform(get("/bookingservice/sum/12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk()) .andReturn();;
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
}
