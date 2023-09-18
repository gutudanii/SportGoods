package com.ecommerce.SportGoods.repository;

import com.ecommerce.SportGoods.model.Cart;
import org.hibernate.sql.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> getByUserId(Long userId);
    Optional<Cart> getByOrderId(Long orderId);

    List<Cart> getAllByUserId(Long userId);
}
