package com.example.springneedthisbackend.controller;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Message;
import com.example.springneedthisbackend.service.AppUserService;
import com.example.springneedthisbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AppUserService userService;
    @PostMapping("/chat/{chatId}")
    public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt,
                                 @PathVariable Long chatId) throws Exception {
        AppUser user=userService.findUserProfileByJwt(jwt);
        Message message = messageService.createMessage(user,chatId,req);
        return  message;
    }
    @GetMapping("/chat/{chatId}")
    public List<Message> findChatMessages(@RequestHeader("Authorization") String jwt, @PathVariable Long chatId) throws Exception {
        AppUser user =userService.findUserProfileByJwt(jwt);
        return   messageService.findChatMessages(chatId);
    }
}