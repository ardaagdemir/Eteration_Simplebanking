package com.eteration.simplebanking.model;

import com.eteration.simplebanking.model.Account;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id", "date", "approvalCode", "type"})
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private Double amount;
    private String approvalCode;
    private String type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Account account;


    public Transaction(Double amount, String type) {
        this.amount = amount;
        this.approvalCode = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", approvalCode='" + approvalCode + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
