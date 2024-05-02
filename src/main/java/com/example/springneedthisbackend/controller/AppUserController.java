package com.example.springneedthisbackend.controller;


import com.example.springneedthisbackend.Util.UserUtil;
import com.example.springneedthisbackend.dto.AppUserDto;
import com.example.springneedthisbackend.dto.mapper.AppUserDtoMapper;
import com.example.springneedthisbackend.exception.UserException;
import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appUser")
public class AppUserController {



    @Autowired
    private AppUserService userService;

    @GetMapping("/profile")
    public ResponseEntity<AppUserDto> getUserProfile(@RequestHeader("Authorization") String jwt
                                                     ) throws UserException{
        AppUser appUser = userService.findUserProfileByJwt(jwt);
        AppUserDto userDto = AppUserDtoMapper.toAppUserDto(appUser);
        userDto.setReq_user(true);
        return new ResponseEntity<AppUserDto>(userDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<AppUserDto> getUserById(@RequestHeader("Authorization") String jwt , @PathVariable Long userId
    ) throws UserException{
        AppUser reqUser = userService.findUserProfileByJwt(jwt);
        AppUser appUser1 = userService.findUserById(userId);
        AppUserDto userDto = AppUserDtoMapper.toAppUserDto(appUser1);
        userDto.setReq_user(UserUtil.isReqUser(reqUser,appUser1));
        userDto.setFollowed(UserUtil.isFollwedByReqUser(reqUser,appUser1));
        return new ResponseEntity<AppUserDto>(userDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/search")
    public ResponseEntity<List<AppUserDto>> searchUser(@RequestHeader("Authorization") String jwt , @RequestParam String query
    ) throws UserException{
        AppUser reqUser = userService.findUserProfileByJwt(jwt);
        List<AppUser> users = userService.searchUser(query);
        List<AppUserDto> appUserDtos = AppUserDtoMapper.toAppUserDtos(users);
        return new ResponseEntity<>(appUserDtos, HttpStatus.ACCEPTED);
    }
    @PutMapping("/{userId}/follow")
    public ResponseEntity<AppUserDto> followUser(@RequestHeader("Authorization") String jwt , @PathVariable Long userId
    ) throws UserException{
        AppUser reqUser = userService.findUserProfileByJwt(jwt);
        AppUser user = userService.followUser(userId,reqUser);
        AppUserDto appUserDto = AppUserDtoMapper.toAppUserDto(user);
        appUserDto.setFollowed(UserUtil.isFollwedByReqUser(reqUser,user));
        return new ResponseEntity<>(appUserDto, HttpStatus.ACCEPTED);
    }

}
