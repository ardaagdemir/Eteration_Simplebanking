package com.eteration.simplebanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
public class DepositTransaction extends Transaction {
    public DepositTransaction(Double amount) {
        super(amount,DepositTransaction.class.getSimpleName());
    }
}
