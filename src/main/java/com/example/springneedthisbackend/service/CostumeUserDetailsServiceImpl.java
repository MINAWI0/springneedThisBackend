package com.example.springneedthisbackend.service;


import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostumeUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findUsersByEmail(username);

        if(appUser == null || appUser.isLogin_with_google()){
            throw new UsernameNotFoundException("username not found" + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(appUser.getEmail(),appUser.getPassword(),authorities);

}}
