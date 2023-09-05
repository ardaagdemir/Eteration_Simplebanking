package com.eteration.simplebanking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id"})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String phone;
    private String payee;
    /*@ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Account account;*/
    @OneToOne(cascade = CascadeType.ALL)
    private WithdrawalTransaction transaction;
    public Payment(Double amount, String phone, String type, String payee) {
        this.phone = phone;
        this.transaction = new WithdrawalTransaction(amount);
        this.transaction.setType(type);
        this.payee = payee;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
