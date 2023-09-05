package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.request.CreateAccountRequestDto;
import com.eteration.simplebanking.dto.response.CreateAccountPaymentResponseDto;
import com.eteration.simplebanking.dto.response.CreateAccountResponseDto;
import com.eteration.simplebanking.dto.response.TransactionResponseDto;
import com.eteration.simplebanking.mapper.IMapper;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.model.exception.ErrorType;
import com.eteration.simplebanking.model.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.repository.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final IAccountRepository accountRepository;
    private final TransactionService transactionService;

    public Account createAccount(CreateAccountRequestDto createAccountRequestDto) {
        Account account = Account.builder()
                .owner(createAccountRequestDto.getOwner())
                .accountNumber(createAccountRequestDto.getAccountNumber())
                .build();
        return accountRepository.save(account);
    }

    public Account findAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> {
            throw new InsufficientBalanceException(ErrorType.ACCOUNT_NOT_FOUND);
        });
        return account;
    }

    public CreateAccountResponseDto findAccountSetToDto(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> {
            throw new InsufficientBalanceException(ErrorType.ACCOUNT_NOT_FOUND);
        });
        List<Transaction> transactions = transactionService.getTransactionByAccountId(account.getId());

        List<TransactionResponseDto> transactionDto = IMapper.INSTANCE.fromTransactionToTransactionResponseDto(transactions);
        CreateAccountResponseDto accountDto = CreateAccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .owner(account.getOwner())
                .balance(account.getBalance())
                .createDate(account.getCreateDate())
                .transactions(transactionDto)
                .build();
        return accountDto;
    }
}











