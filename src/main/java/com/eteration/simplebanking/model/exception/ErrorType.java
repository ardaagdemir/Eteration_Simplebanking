package com.eteration.simplebanking.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    BALANCE_ERROR(4001, "Bakiyeniz yetersiz", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_FOUND(4002, "Hesap numarası geçersizdir", HttpStatus.BAD_REQUEST)
    ;

    private int code;
    private String message;
    HttpStatus httpStatus;
}
