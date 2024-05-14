package com.example.springneedthisbackend.controller;


import com.example.springneedthisbackend.config.JwtProvider;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.repo.UserRepository;
import com.example.springneedthisbackend.response.AuthResponse;
import com.example.springneedthisbackend.service.CostumeUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CostumeUserDetailsServiceImpl userDetailsService;



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> creatUserHandler(@RequestBody AppUser appUser) throws UserException {

        String email = appUser.getEmail();
        String password = appUser.getPassword();
        String fullName = appUser.getFullName();
        String birthDate = appUser.getBirthDate();
        Boolean iseller = appUser.isSeller();
        AppUser isEmailExiste = userRepository.findUsersByEmail(email);
        if(isEmailExiste !=null){
            throw new UserException("Email is already taken by another account");
        }
        AppUser createdAppUser = new AppUser();
        createdAppUser.setEmail(email);
        createdAppUser.setPassword(passwordEncoder.encode(password));
        createdAppUser.setFullName(fullName);
        createdAppUser.setBirthDate(birthDate);
        createdAppUser.setSeller(iseller);

        AppUser savedUser = userRepository.save(createdAppUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.genrateToken(authentication);
        AuthResponse response = new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody AppUser appUser){
        String appUseremail = appUser.getEmail();
        String password  = appUser.getPassword();
        Authentication authentication = authenticate(appUseremail,password);
        String token = jwtProvider.genrateToken(authentication);
        AuthResponse response = new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(response, HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String appUseremail, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(appUseremail);
        if(userDetails == null){
            throw new BadCredentialsException("Invalide Email ... :< ");
        }
        if( ! passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalide email or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
