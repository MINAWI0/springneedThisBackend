package com.example.springneedthisbackend.Util;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Like;
import com.example.springneedthisbackend.model.Request;

public class RequestUtil {
    public final static boolean isLikedByUser(AppUser appUser, Request request){
        for(Like like :  request.getLikes()){
            if(like.getUser().getId().equals(appUser.getId())){
                return true;
            }
        }

        return false;
    }

    public final static boolean isReRequest(AppUser appUser,Request request){
        for (AppUser user : request.getReRequestUser()){
            if(user.getId().equals(appUser.getId())) {
                return true;
            }
        }
        return false;
    }
}
