package com.example.springneedthisbackend.service.implemenation;


import com.example.springneedthisbackend.config.JwtProvider;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.repo.UserRepository;
import com.example.springneedthisbackend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AppUser findUserById(Long appUserId) throws UserException {
            AppUser appUser = userRepository.findById(appUserId).orElseThrow(()->new UserException("user not found" + appUserId));
            return appUser;
    }

    @Override
    public AppUser findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        AppUser appUser = userRepository.findUsersByEmail(email);
        if(appUser == null) {
            throw new UserException("user not found");
        }
        return appUser;
    }

    @Override
    public AppUser updateUser(Long appUserId, AppUser appUser) throws UserException {
        AppUser appUserToUpdate = findUserById(appUserId);
        if(appUser.getFullName() != null){
            appUserToUpdate.setFullName(appUser.getFullName());
        }
        if(appUser.getImage()!= null){
            appUserToUpdate.setImage(appUser.getImage());
        }
        if(appUser.getBackgroundImage() != null){
            appUserToUpdate.setBackgroundImage(appUser.getBackgroundImage());
        }
        if(appUser.getBirthDate() != null){
            appUserToUpdate.setBirthDate(appUser.getBirthDate());
        }
        if(appUser.getLocation() != null){
            appUserToUpdate.setLocation(appUser.getLocation());
        }
        if(appUser.getBio() != null){
            appUserToUpdate.setBio(appUser.getBio());
        }

        return userRepository.save(appUserToUpdate);

    }

    @Override
    public AppUser followUser(Long appUserId, AppUser appUser) throws UserException {

        AppUser followToUser = findUserById(appUserId);
        if(appUser.getFollowings().contains(followToUser) && followToUser.getFollowers().contains(appUser)){
            appUser.getFollowings().remove(followToUser);
            followToUser.getFollowers().remove(appUser);
        }else {
            appUser.getFollowings().add(followToUser);
            followToUser.getFollowers().add(appUser);
        }
        userRepository.save(followToUser);
        userRepository.save(appUser);
        return followToUser;
    }

    @Override
    public List<AppUser> searchUser(String query) {

        return userRepository.searchUserBy(query);
    }
}
