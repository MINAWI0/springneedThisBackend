package com.example.springneedthisbackend.dto.mapper;

import com.example.springneedthisbackend.Util.RequestUtil;
import com.example.springneedthisbackend.dto.AppUserDto;
import com.example.springneedthisbackend.dto.RequestDto;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestMapper {

    public static RequestDto torequestDto(Request request , AppUser appUser){

        AppUserDto user = AppUserDtoMapper.toAppUserDto(request.getAppUser());
        boolean isLiked = RequestUtil.isLikedByUser(appUser,request);
        boolean isReRequest = RequestUtil.isReRequest(appUser,request);
        List<Long> reRequestAppUserId = new ArrayList<>();

        for(AppUser appUser1 : request.getReRequestUser()){
            reRequestAppUserId.add(appUser1.getId());
        }
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setContent(request.getContent());
        requestDto.setCreatedAt(request.getCreatedAt());
        requestDto.setImage(request.getImage());
        requestDto.setTotalLikes(request.getLikes().size());
        requestDto.setTotalReRequests(request.getReRequestUser().size());
        requestDto.setTotalReplies(request.getReplyRequests().size());
        requestDto.setTotalReplies(request.getReplyRequests().size());
        requestDto.setUser(user);
        requestDto.setLiked(isLiked);
        requestDto.setReRequest(isReRequest);
        requestDto.setReRequestUsersld(reRequestAppUserId);
        requestDto.setReplyRequests(toRequestDtos(request.getReplyRequests() , appUser));
        requestDto.setVideo(request.getVideo());
        requestDto.setPrice(request.getPrice());
        requestDto.setMaxPrice(request.getMaxPrice());
        requestDto.setMinPrice(request.getMinPrice());
        requestDto.setCategory(request.getCategory());
        requestDto.setLocation(request.getLocation());

        return requestDto;

    }
    public static List<RequestDto> toRequestDtos(List<Request> requests , AppUser reqUser){
        List<RequestDto> requestDtos = new ArrayList<>();
        for(Request request:requests){
            RequestDto requestDto = toReplyRequestDto(request , reqUser);
                requestDtos.add(requestDto);
        }
        return requestDtos;
    }

    private static RequestDto toReplyRequestDto(Request request, AppUser reqUser) {
        AppUserDto user = AppUserDtoMapper.toAppUserDto(request.getAppUser());
        boolean isLiked = RequestUtil.isLikedByUser(reqUser,request);
        boolean isReRequest = RequestUtil.isReRequest(reqUser,request);
        List<Long> reRequestAppUserId = new ArrayList<>();

        for(AppUser appUser1 : request.getReRequestUser()){
            reRequestAppUserId.add(appUser1.getId());
        }
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setContent(request.getContent());
        requestDto.setCreatedAt(request.getCreatedAt());
        requestDto.setImage(request.getImage());
        requestDto.setTotalLikes(request.getLikes().size());
        requestDto.setTotalReRequests(request.getReRequestUser().size());
        requestDto.setTotalReplies(request.getReplyRequests().size());
        requestDto.setUser(user);
        requestDto.setLiked(isLiked);
        requestDto.setReRequest(isReRequest);
        requestDto.setReRequestUsersld(reRequestAppUserId);
        requestDto.setVideo(request.getVideo());
        requestDto.setPrice(request.getPrice());
        requestDto.setMaxPrice(request.getMaxPrice());
        requestDto.setMinPrice(request.getMinPrice());
        requestDto.setCategory(request.getCategory());
        requestDto.setLocation(request.getLocation());
        requestDto.setRequestType(request.isRequestType());



        return requestDto;
    }
}
