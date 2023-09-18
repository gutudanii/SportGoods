package com.ecommerce.SportGoods.repository;

import com.ecommerce.SportGoods.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepo extends JpaRepository<Shipping, Long> {
}
