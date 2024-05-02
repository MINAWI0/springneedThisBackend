package com.example.springneedthisbackend.service;

import com.example.springneedthisbackend.exception.RequestException;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Request;
import com.example.springneedthisbackend.request.RequestReplyOffre;

import java.util.List;

public interface RequestService {
    public Request createRequest(Request req, AppUser user)throws UserException , RequestException;
    public List<Request> findAllRequest();
    public Request reRequest(Long requestId, AppUser user) throws UserException, RequestException;
    public Request findRequestById(Long requestId) throws RequestException;
    public void deleteRequestById(Long requestId, Long userld) throws RequestException, UserException;
    public Request removeFromReRequest(Long requestId, AppUser user) throws RequestException, UserException;
    public Request createdReply(RequestReplyOffre req, AppUser user) throws RequestException;
    public List<Request>  getAppUserRequest(AppUser appUser);
    public List<Request> findByLikesContainsAppUser(AppUser appUser);
}
