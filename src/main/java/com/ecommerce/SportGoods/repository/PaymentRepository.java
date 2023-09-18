package com.ecommerce.SportGoods.repository;

import com.ecommerce.SportGoods.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> getAllPaymentsDoneByUserId(Long userId);
    Optional<Payment> getPaymentsDoneByUserId(Long userId);

 }
