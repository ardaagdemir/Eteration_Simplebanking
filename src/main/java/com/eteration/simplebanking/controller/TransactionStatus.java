package com.eteration.simplebanking.controller;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TransactionStatus {
    public String status;
    public String approvalCode;

    public TransactionStatus() {}

    public TransactionStatus(String status, String approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }

    @Override
    public String toString() {
        return "TransactionStatus{" +
                "status='" + status + '\'' +
                ", approvalCode='" + approvalCode + '\'' +
                '}';
    }
}
