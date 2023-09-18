package com.ecommerce.SportGoods.service;

import com.ecommerce.SportGoods.model.Shipping;
import org.springframework.stereotype.Service;

@Service
public interface ShippingService {
    void saveShip(Shipping shipping);

}
