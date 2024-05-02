package com.example.springneedthisbackend.service;


import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;

import java.util.List;

public interface AppUserService {

    public AppUser findUserById(Long appUserId) throws UserException;

    public AppUser findUserProfileByJwt(String jwt) throws UserException;

    public AppUser updateUser(Long appUserId , AppUser appUser) throws UserException;
    public AppUser followUser(Long appUserId ,AppUser appUser) throws UserException;

    public List<AppUser> searchUser(String query);
}
