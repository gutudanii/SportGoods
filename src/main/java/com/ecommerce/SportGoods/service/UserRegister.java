package com.ecommerce.SportGoods.service;

import com.ecommerce.SportGoods.model.Users;
import com.ecommerce.SportGoods.repository.UsersRepository;
import com.ecommerce.SportGoods.session.Session;
import com.ecommerce.SportGoods.session.SessionHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegister implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username).get();
        SessionHandler.setSession(new Session(users.getUsername()));
        return usersRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("This is email is not found " + username)));
    }

}
