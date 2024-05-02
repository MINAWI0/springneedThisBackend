package com.example.springneedthisbackend.service;


import com.example.springneedthisbackend.exception.RequestException;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Like;

import java.util.List;

public interface LikeService {

    public Like likeRequest(Long requestId , AppUser appUser) throws UserException, RequestException;
     public List<Like> getAllLikes(Long requestId) throws RequestException;
}
