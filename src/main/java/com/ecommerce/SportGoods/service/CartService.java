package com.ecommerce.SportGoods.service;

import com.ecommerce.SportGoods.model.Cart;
import com.ecommerce.SportGoods.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CartService {
    void saveCart(Cart cart);
    List<Cart> getAllCart();
    Optional<Cart> getCart(Long id);
    List<Cart> getAllCartByUserId(Long id);
    void deleteCart(Long id);
    void deleteByOrderId(Long id);
    Users updateCart(Cart cart, Long id);
}

