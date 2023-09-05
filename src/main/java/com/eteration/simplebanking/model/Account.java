package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.exception.ErrorType;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private String accountNumber;
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();
    @Builder.Default
    private Double balance = 0d;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Transaction> transactions;

    /*
    Task-1, constructor for test case
    */
    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        if (getTransactions() == null) {
            this.transactions = new ArrayList<>();
        }
    }

    /*
    Task-1
     */
    public Transaction post(Transaction transaction) {
        if (transaction instanceof DepositTransaction) {
            credit(transaction.getAmount());
        } else if (transaction instanceof WithdrawalTransaction) {
            debit(transaction.getAmount());
        } /*else if (this.payments instanceof PhoneBillPaymentTransaction) {
            debit(transaction.getAmount());
        }*/
        getTransactions().add(transaction);
        transaction.setAccount(this);
        return transaction;
    }

    /*
    Task-2
     */
    public void credit(Double amount) {
        balance += amount;
    }

    public void debit(Double amount) {
        if (getBalance() >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientBalanceException(ErrorType.BALANCE_ERROR);
        }
    }

    /*public void payPhoneBill(double amount) throws InsufficientBalanceException {
        debit(amount);
    }*/

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
