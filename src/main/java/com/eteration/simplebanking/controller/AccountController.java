package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.request.CreateAccountRequestDto;
import com.eteration.simplebanking.dto.response.CreateAccountResponseDto;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.IAccountRepository;
import com.eteration.simplebanking.repository.IPaymentRepository;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.PaymentService;
import com.eteration.simplebanking.services.TransactionService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/v1")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final IAccountRepository accountRepository;
    private final TransactionService transactionService;
    private final PaymentService paymentService;

    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequestDto dto) {
        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody DepositTransaction depositTransaction) {
        Account account = accountService.findAccount(accountNumber);
        Transaction transaction = account.post(new DepositTransaction(depositTransaction.getAmount()));
        accountRepository.save(account);
        return ResponseEntity.ok(new TransactionStatus("OK", transaction.getApprovalCode()));

    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody WithdrawalTransaction withdrawTransaction) {
        Account account = accountService.findAccount(accountNumber);
        Transaction transaction = account.post(new WithdrawalTransaction(withdrawTransaction.getAmount()));
        transactionService.save(withdrawTransaction);
        return ResponseEntity.ok(new TransactionStatus("OK", transaction.getApprovalCode()));
    }

    @PostMapping("/phoneBillPayment/{accountNumber}")
    public ResponseEntity<TransactionStatus> phoneBillPayment(@PathVariable String accountNumber, @RequestBody PhoneBillPaymentTransaction phonePayment) {
        Account account = accountService.findAccount(accountNumber);
        Payment payment = new PhoneBillPaymentTransaction(phonePayment.getTransaction().getAmount(), phonePayment.getPhone(), phonePayment.getPayee());
        paymentService.save(payment);
        account.post(payment.getTransaction());
        transactionService.save(payment.getTransaction());
        return ResponseEntity.ok(new TransactionStatus("OK", payment.getTransaction().getApprovalCode()));
    }

    /*
     * For test case
     */
    @Hidden
    @GetMapping("/get-account/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {;
        return new ResponseEntity<>(accountService.findAccount(accountNumber), HttpStatus.OK);
    }

    @GetMapping("/get-account-all-data/{accountNumber}")
    public ResponseEntity<CreateAccountResponseDto> getAccountAllData(@PathVariable String accountNumber) {;
        return new ResponseEntity<>(accountService.findAccountSetToDto(accountNumber), HttpStatus.OK);
    }
}