package com.example.springneedthisbackend.service.implemenation;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Chat;
import com.example.springneedthisbackend.repo.ChatRepository;
import com.example.springneedthisbackend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Chat createChat(AppUser reqUser, AppUser user2) {
        Chat isExit=chatRepository.findChatByUsersId(user2,reqUser);
        if(isExit!=null){
            return isExit;
        }
        Chat chat=new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Long chatId) throws Exception {
        Optional<Chat> opt =chatRepository.findById(chatId);
        if(opt.isEmpty()){
            throw new Exception("chat not found with id -"+chatId);
        }
        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Long userId) {

        return chatRepository.findByUsersId(userId);
    }
}