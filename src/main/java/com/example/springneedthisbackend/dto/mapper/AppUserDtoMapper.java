package com.example.springneedthisbackend.dto.mapper;

import com.example.springneedthisbackend.dto.AppUserDto;
import com.example.springneedthisbackend.model.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserDtoMapper {
    public static AppUserDto toAppUserDto (AppUser user) {
        if (user == null) {
            return null; // or throw an IllegalArgumentException
        }

        AppUserDto userDto=new AppUserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFullName(user.getFullName());
        userDto.setImage(user.getImage());
        userDto.setBackgoundImage(user.getBackgroundImage());
        userDto.setBio(user.getBio());
        userDto.setSeller(user.isSeller());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setFollowers(toAppUserDtos(user.getFollowers()));
        userDto.setFollowing(toAppUserDtos(user.getFollowings()));
        userDto.setLogin_with_google(user.isLogin_with_google());
        userDto.setLocation(user.getLocation());
        userDto.setFacebook(user.getFacebook());
        userDto.setTwitter(user.getTwitter());
        userDto.setInstagram(user.getInstagram());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setProfessionalEmail(user.getProfessionalEmail());
        userDto.setTermsAndService(user.getTermsAndService());
        userDto.setCompanyName(user.getCompanyName());

        return userDto;
    }
    public static List<AppUserDto> toAppUserDtos(List<AppUser> followers) {
        List<AppUserDto> userDtos=new ArrayList<>();
        for(AppUser user: followers) {
            AppUserDto userDto=new AppUserDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getImage());
            userDto.setFullName(user.getFullName());
            userDto.setImage(user.getImage());
            userDtos.add(userDto);
            userDto.setFacebook(user.getFacebook());
            userDto.setTwitter(user.getTwitter());
            userDto.setInstagram(user.getInstagram());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setProfessionalEmail(user.getProfessionalEmail());
            userDto.setTermsAndService(user.getTermsAndService());
            userDto.setCompanyName(user.getCompanyName());
        }
        return userDtos;
    }}