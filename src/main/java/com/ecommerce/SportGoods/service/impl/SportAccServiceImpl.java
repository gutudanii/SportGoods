package com.ecommerce.SportGoods.service.impl;

import com.ecommerce.SportGoods.model.SportAcc;
import com.ecommerce.SportGoods.repository.SportAccRepository;
import com.ecommerce.SportGoods.service.SportAccService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SportAccServiceImpl implements SportAccService {

    @Autowired
    SportAccRepository sportAccRepository;

    @Override
    public List<SportAcc> getAllByType(String type) {
        return sportAccRepository.getAllByType(type);
    }

    @Override
    public List<SportAcc> getAllSportAccs() {
        return sportAccRepository.findAll();
    }

    @Override
    public SportAcc getSportAccById(Long id) {
        Optional<SportAcc> optionalSportAcc = sportAccRepository.findById(id);
        return optionalSportAcc.orElse(null);
    }

    @Override
    public SportAcc createSportAcc(SportAcc sportAcc) {
        return sportAccRepository.save(sportAcc);
    }

    @Override
    public List<SportAcc> getAllSportAccByOrderId(Long orderId) {
        return sportAccRepository.getAllSportAccBySportId(orderId);
    }

    @Override
    public SportAcc updateSportAcc(Long id, SportAcc sportAcc) {
        if (sportAccRepository.existsById(id)) {
            sportAcc.setSportId(id);
            return sportAccRepository.save(sportAcc);
        }
        return null;
    }

    @Override
    public void deleteSportAcc(Long id) {
        sportAccRepository.deleteById(id);
    }
}

