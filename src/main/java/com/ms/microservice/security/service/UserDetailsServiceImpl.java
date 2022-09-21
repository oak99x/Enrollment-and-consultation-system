package com.ms.microservice.security.service;

import com.ms.microservice.security.users.UserModel;
import com.ms.microservice.security.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User nao Encontrado :: " + username));

        return user;
    }

    public UserModel saveUser(UserModel user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return  userRepository.save(user);
    }
}
