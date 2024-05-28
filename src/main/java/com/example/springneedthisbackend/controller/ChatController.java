package com.example.springneedthisbackend.controller;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Chat;
import com.example.springneedthisbackend.request.CreateChatRequest;
import com.example.springneedthisbackend.service.AppUserService;
import com.example.springneedthisbackend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    AppUserService userService;
    @PostMapping("/api/chats")
    public Chat creatChat(@RequestHeader("Authorization")  String jwt, @RequestBody CreateChatRequest req) throws Exception {
        AppUser appUser = userService.findUserProfileByJwt(jwt);
        AppUser reqUser = userService.findUserById(appUser.getId());
        AppUser user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) throws Exception {
        AppUser appUser = userService.findUserProfileByJwt(jwt);
        List<Chat> chats = chatService.findUsersChat(appUser.getId());
        return chats;
    }

}