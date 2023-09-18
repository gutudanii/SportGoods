package com.ecommerce.SportGoods.repository;

import com.ecommerce.SportGoods.model.SportAcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportAccRepository extends JpaRepository<SportAcc, Long> {
    List<SportAcc> findByOrderNameContainingIgnoreCase(String orderName);

    List<SportAcc> getAllSportAccBySportId(Long sportId);
    List<SportAcc> getAllByType(String type);

}
