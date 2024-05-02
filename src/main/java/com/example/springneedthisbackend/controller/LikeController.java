package com.example.springneedthisbackend.controller;


import com.example.springneedthisbackend.dto.LikeDto;
import com.example.springneedthisbackend.dto.RequestDto;
import com.example.springneedthisbackend.dto.mapper.LikeDtoMapper;
import com.example.springneedthisbackend.dto.mapper.RequestMapper;
import com.example.springneedthisbackend.exception.RequestException;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Like;
import com.example.springneedthisbackend.service.AppUserService;
import com.example.springneedthisbackend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LikeController {
    @Autowired
    private AppUserService userService;
    @Autowired
    private LikeService likeService;


    @PostMapping("/{requestId}/likes")
    public ResponseEntity<LikeDto> likeRequest(@PathVariable Long requestId , @RequestHeader("Authorization") String jwt) throws UserException,RequestException {
        AppUser appUser = userService.findUserProfileByJwt(jwt);
        Like like = likeService.likeRequest(requestId,appUser);
        LikeDto likeDto = LikeDtoMapper.tolikeDto(like , appUser);
        return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);
    }

    @PostMapping("/request/{requestId}")
    public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long requestId , @RequestHeader("Authorization") String jwt) throws UserException,RequestException {
        AppUser appUser = userService.findUserProfileByJwt(jwt);
        List<Like> likes = likeService.getAllLikes(requestId);
        List<LikeDto> likeDtos = LikeDtoMapper.toLikeDtos(likes , appUser);
        return new ResponseEntity<>(likeDtos, HttpStatus.CREATED);
    }
}
