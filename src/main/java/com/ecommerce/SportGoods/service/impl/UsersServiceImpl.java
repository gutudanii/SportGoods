package com.ecommerce.SportGoods.service.impl;

import com.ecommerce.SportGoods.model.Users;
import com.ecommerce.SportGoods.repository.UsersRepository;
import com.ecommerce.SportGoods.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    @Autowired
    PasswordEncoder pswd;

    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public void saveUser(Users users) {
        users.setPassword(pswd.encode(users.getPassword()));
        users.setRole("user");
        usersRepository.save(users);
    }

    @Override
    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<Users> getUser(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users updateUser(Long id) {
        Users userExist = usersRepository.findById(id).orElse(null);

        if (userExist != null) {
            userExist.setUsername(userExist.getUsername());
            userExist.setEmailAddress(userExist.getEmailAddress());
            userExist.setPassword(userExist.getPassword());
            userExist.setFullName(userExist.getFullName());
            userExist.setRole(userExist.getRole());
            userExist.setBlock(false);
            userExist.setPaypal(userExist.getPaypal());
            userExist.setTeleBirr(userExist.getTeleBirr());

            usersRepository.save(userExist);
        }

        return userExist;
    }

}
