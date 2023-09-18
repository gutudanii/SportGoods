package com.ecommerce.SportGoods.controller;

import com.ecommerce.SportGoods.model.Shipping;
import com.ecommerce.SportGoods.service.ShippingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ShippingController {

    @Autowired
    private final ShippingService shippingService;

    @PostMapping("/shipping/save")
    public String saveShipping(@ModelAttribute Shipping shipping){
        shippingService.saveShip(shipping);
        return "redirect:/users/carts";
    }
}
