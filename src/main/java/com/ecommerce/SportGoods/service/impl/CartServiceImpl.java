package com.ecommerce.SportGoods.service.impl;

import com.ecommerce.SportGoods.model.Cart;
import com.ecommerce.SportGoods.model.Users;
import com.ecommerce.SportGoods.repository.CartRepository;
import com.ecommerce.SportGoods.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;
    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> getCart(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> getAllCartByUserId(Long id) {
        return cartRepository.getAllByUserId(id);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteByOrderId(Long id) {
        Cart crt = cartRepository.getByOrderId(id).get();
        cartRepository.deleteById(crt.getOrderId());
    }

    @Override
    public Users updateCart(Cart cart, Long id) {
        return null;
    }
}
