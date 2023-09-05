package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Payment;
import com.eteration.simplebanking.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final IPaymentRepository paymentRepository;

    public void save(Payment payment){
        paymentRepository.save(payment);
    }
}
