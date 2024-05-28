package com.example.springneedthisbackend.repo;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository  extends JpaRepository<Chat,Long> {
    public List<Chat> findByUsersId(Long id);
    @Query("select c from Chat c where  :user member of c.users and :reqUser MEMBER of c.users")
    public Chat findChatByUsersId(@Param("user") AppUser user, @Param("reqUser") AppUser reqUser);

}