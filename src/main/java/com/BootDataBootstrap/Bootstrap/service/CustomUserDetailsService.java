package com.BootDataBootstrap.Bootstrap.service;

import com.BootDataBootstrap.Bootstrap.dao.UsersRepository;
import com.BootDataBootstrap.Bootstrap.model.CustomUserDetails;
import com.BootDataBootstrap.Bootstrap.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByEmail(email);

        optionalUsers
                .orElseThrow(()->new UsernameNotFoundException("Email not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();

    }
}
