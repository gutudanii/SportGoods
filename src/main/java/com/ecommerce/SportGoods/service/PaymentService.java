package com.ecommerce.SportGoods.service;

import com.ecommerce.SportGoods.model.Payment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public interface PaymentService {

    void createPayment(Payment payment, Authentication authentication);
    Payment payment(Payment payment, Long id);
    List<Payment> getAllPayment();
    Optional<Payment> getPaymentById(Long id);

    Payment approvePayment(Long id);
    Payment approvePayments(Long id);
}
