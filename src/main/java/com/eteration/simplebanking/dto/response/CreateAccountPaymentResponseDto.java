package com.eteration.simplebanking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAccountPaymentResponseDto {
    private String accountNumber;
    private String owner;
    private Double balance;
    private LocalDateTime createDate;
    private List<TransactionResponseDto> transactions;
    private String payee;
    private String phone;
}
