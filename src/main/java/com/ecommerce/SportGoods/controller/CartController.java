package com.ecommerce.SportGoods.controller;

import com.ecommerce.SportGoods.model.Cart;
import com.ecommerce.SportGoods.model.Users;
import com.ecommerce.SportGoods.repository.CartRepository;
import com.ecommerce.SportGoods.repository.UsersRepository;
import com.ecommerce.SportGoods.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class CartController {

    @Autowired
    private final CartService cartService;

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final UsersRepository usersRepository;
    @PostMapping("/addCart")
    public String addCart(@ModelAttribute Cart cart, Principal principal){
        Users users = usersRepository.findByUsername(principal.getName()).get();
        cart.setUserId(users.getUserId());
        cartService.saveCart(cart);
        return "redirect:/home";
    }

    @GetMapping("/getAll/cart")
    public String getAll(Model model){
        List<Cart> cart = cartService.getAllCart();
        model.addAttribute("cart", cart);
        return "redirect:/homeCart";
    }

    @RequestMapping({"/carts/delete","/carts/delete/{id}"})
    public String deleteCart(@PathVariable("id") Long id){
        Cart crt = cartRepository.getByOrderId(id).get();
        cartRepository.deleteById(crt.getCartId());
        return "redirect:/users/carts";
    }
}
