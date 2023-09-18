package com.ecommerce.SportGoods.service;

import com.ecommerce.SportGoods.model.SportAcc;
import com.ecommerce.SportGoods.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SportAccService {
    List<SportAcc> getAllByType(String type);
    List<SportAcc> getAllSportAccs();

    SportAcc getSportAccById(Long id);

    SportAcc createSportAcc(SportAcc sportAcc);

    List<SportAcc> getAllSportAccByOrderId(Long orderId);

    SportAcc updateSportAcc(Long id, SportAcc sportAcc);

    void deleteSportAcc(Long id);
}
