package com.example.springneedthisbackend.dto.mapper;


import com.example.springneedthisbackend.dto.AppUserDto;
import com.example.springneedthisbackend.dto.LikeDto;
import com.example.springneedthisbackend.dto.RequestDto;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Like;

import java.util.ArrayList;
import java.util.List;

public class LikeDtoMapper {
    public static LikeDto tolikeDto(Like like , AppUser appUser){
        AppUserDto user = AppUserDtoMapper.toAppUserDto(like.getUser());
        AppUserDto reqUserDto = AppUserDtoMapper.toAppUserDto(appUser);
        RequestDto request = RequestMapper.torequestDto(like.getRequest(),appUser);

        LikeDto likeDto = new LikeDto();
        likeDto.setId(like.getId());
        likeDto.setRequest(request);
        likeDto.setUser(user);

        return likeDto;
    }

    public static List<LikeDto> toLikeDtos(List<Like> likes , AppUser appUser){
        List<LikeDto> likeDtos = new ArrayList<>();
        for (Like like : likes){
            AppUserDto user = AppUserDtoMapper.toAppUserDto(like.getUser());
            RequestDto request = RequestMapper.torequestDto(like.getRequest(),appUser);
            LikeDto likeDto = new LikeDto();
            likeDto.setId(like.getId());
            likeDto.setRequest(request);
            likeDto.setUser(user);
            likeDtos.add(likeDto);
        }
        return likeDtos;
    }
}
