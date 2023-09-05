package com.eteration.simplebanking.model;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class PhoneBillPaymentTransaction extends Payment {

    public PhoneBillPaymentTransaction(Double amount, String phone, String payee) {
        super(amount,phone, PhoneBillPaymentTransaction.class.getSimpleName(), payee);
    }
}
