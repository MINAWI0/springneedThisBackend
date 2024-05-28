package com.example.springneedthisbackend.model;

import com.example.springneedthisbackend.model.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chat_name;//username?
    private String chat_image;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "chat_users",
            joinColumns = @JoinColumn(name = "id_chat",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_user",referencedColumnName = "id"))
    private List<AppUser> users  = new ArrayList<>();
    private LocalDateTime timestamp;
    @OneToMany(mappedBy = "chat")
    private List<Message> messages=new ArrayList<>();

}