package com.ecommerce.SportGoods.service.impl;

import com.ecommerce.SportGoods.model.Shipping;
import com.ecommerce.SportGoods.repository.ShippingRepo;
import com.ecommerce.SportGoods.service.ShippingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private final ShippingRepo shippingRepo;

    @Override
    public void saveShip(Shipping shipping) {
        shippingRepo.save(shipping);
    }
}
