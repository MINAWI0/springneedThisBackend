package com.example.springneedthisbackend.Util;

import com.example.springneedthisbackend.model.AppUser;

public class UserUtil {

    public static final boolean isReqUser(AppUser reqUser , AppUser user){
        return reqUser.getId().equals(user.getId());
    }

    public static final boolean isFollwedByReqUser(AppUser reqUser , AppUser user){
        return reqUser.getFollowings().contains(user);
    }
}
