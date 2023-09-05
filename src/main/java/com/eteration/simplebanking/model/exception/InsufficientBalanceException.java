package com.eteration.simplebanking.model.exception;

import lombok.Getter;

@Getter
public class InsufficientBalanceException extends RuntimeException {
    private final ErrorType errorType;
    public InsufficientBalanceException( ErrorType errorType) {
        this.errorType = errorType;
    }
}
