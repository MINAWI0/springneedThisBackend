package com.example.springneedthisbackend.service.implemenation;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Chat;
import com.example.springneedthisbackend.model.Message;
import com.example.springneedthisbackend.repo.ChatRepository;
import com.example.springneedthisbackend.repo.MessageRepository;
import com.example.springneedthisbackend.service.ChatService;
import com.example.springneedthisbackend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
//        @Autowired
//    private MessageService messageService;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Message createMessage(AppUser user, Long chatId, Message req) throws Exception {
        Message message=new Message();
        Chat chat=chatService.findChatById(chatId);
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setUser(user);
        message.setImage(req.getImage());
        message.setTimeStamp(req.getTimeStamp());
        Message saveMessage =  messageRepository.save(message);
        chat.getMessages().add(saveMessage);
        chatRepository.save(chat);

        return saveMessage;
    }

    @Override
    public List<Message> findChatMessages(Long chatId) throws Exception {
//         Chat chat=chatService.findChatById(chatId);
        return messageRepository.findByChatId(chatId);
    }
}