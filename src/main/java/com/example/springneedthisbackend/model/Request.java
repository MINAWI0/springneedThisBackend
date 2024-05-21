package com.example.springneedthisbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AppUser appUser;
    private String content;
    private String image;
    private String video;
    private String title;
    private String link;
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();
    @OneToMany
    private List<Request> replyRequests=new ArrayList<>();
    @ManyToMany
    private List<AppUser> reRequestUser=new ArrayList<>();
    @ManyToOne
    private Request replyFor;
    private boolean replyType;
    private boolean requestType;
    private LocalDateTime createdAt;
    private Float price;
    private Float maxPrice;
    private Float minPrice;
    private String category;
    private String location;
    private Double longitude;
    private Double latitude;
    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", replyType=" + replyType +
                ", requestType=" + requestType +
                ", createdAt=" + createdAt +
                ", price=" + price +
                '}';
    }


}