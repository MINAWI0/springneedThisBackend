package com.example.springneedthisbackend.service;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Message;

import java.util.List;


public interface MessageService {
    public Message createMessage(AppUser user, Long chatId, Message message) throws Exception;
    List<Message> findChatMessages(Long chatId) throws Exception;
}