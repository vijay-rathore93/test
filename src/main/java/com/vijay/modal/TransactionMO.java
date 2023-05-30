package com.vijay.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionMO {
    private Integer transactionId;
    private Double amount;
    private String currency;
    private String type;
    private Integer parentId;
}
