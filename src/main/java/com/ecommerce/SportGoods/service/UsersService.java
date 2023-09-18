package com.ecommerce.SportGoods.service;

import com.ecommerce.SportGoods.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsersService {

    void saveUser(Users users);
    List<Users> getAllUser();
    Optional<Users> getUser(Long id);
    void deleteUser(Long id);
    Users updateUser(Long id);
}
