package com.vijay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq",initialValue = 1000,sequenceName = "transaction_seq",allocationSize = 100)
    @Column(name = "transaction_id")
    private Integer transactionId;
    private Double amount;
    private String currency;
    private String type;
    @Column(name = "parent_id")
    private Integer parentId;

}
