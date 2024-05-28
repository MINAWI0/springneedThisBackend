package com.example.springneedthisbackend.service;


import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Chat;

import java.util.List;

public interface ChatService {
    public Chat createChat(AppUser reqUser, AppUser user2);
    public Chat findChatById(Long chatId) throws Exception;
    public List<Chat> findUsersChat(Long userId);
}