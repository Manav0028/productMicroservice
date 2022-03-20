package com.manavs.productMicroservice.services;

import com.manavs.productMicroservice.models.auth_models.CustomUserDetails;
import com.manavs.productMicroservice.models.db_models.User;
import com.manavs.productMicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailServices implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent())
            throw new UsernameNotFoundException(email + " Not Found.");
        else
            return user.map(CustomUserDetails::new).get();
    }
}
