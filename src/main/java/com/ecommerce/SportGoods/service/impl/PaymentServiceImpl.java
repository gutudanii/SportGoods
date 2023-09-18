package com.ecommerce.SportGoods.service.impl;

import com.ecommerce.SportGoods.model.Payment;
import com.ecommerce.SportGoods.model.Users;
import com.ecommerce.SportGoods.repository.PaymentRepository;
import com.ecommerce.SportGoods.repository.UsersRepository;
import com.ecommerce.SportGoods.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final UsersRepository usersRepository;
    @Override
    public void createPayment(Payment payment, Authentication authentication) {
        Users users =  usersRepository.findByUsername(authentication.getName()).get();
        payment.setUserId(users.getUserId());
        paymentRepository.save(payment);
    }

    @Override
    public Payment payment(Payment payment, Long id) {
        return null;
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return Optional.empty();
    }

    @Override
    public Payment approvePayment(Long id) {
        Payment payment = paymentRepository.findById(id).get();
        if (payment != null) {
            payment.setTransactionId(payment.getTransactionId());
            payment.setUserId(payment.getUserId());
            payment.setApproved(true);
            payment.setTransImg(payment.getTransImg());
        }
            payment = paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment approvePayments(Long id) {
        Payment payment = paymentRepository.findById(id).get();
        if (payment != null) {
            payment.setTransactionId(payment.getTransactionId());
            payment.setUserId(payment.getUserId());
            payment.setApproved(false);
            payment.setTransImg(payment.getTransImg());
        }
        payment = paymentRepository.save(payment);
        return payment;
    }
}
