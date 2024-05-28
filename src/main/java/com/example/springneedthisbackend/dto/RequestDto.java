package com.example.springneedthisbackend.dto;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;



@Data
public class RequestDto {
    private Long id;
    private String content;
    private String image;
    private String title;
    private String link;
    private String video;
    private Float price;
    private Float maxPrice;
    private Float minPrice;
    private String category;
    private String location;
    private AppUserDto user;
    private LocalDateTime createdAt;
    private int totalLikes;
    private int totalReplies;
    private int totalReRequests;
    private boolean isLiked;
    private boolean isReRequest;
    private List<Long> reRequestUsersld;
    private List<RequestDto> replyRequests;
    private boolean requestType;
    private Double longitude;
    private Double latitude;
    private boolean closed;
    private AppUser closedBy;



}
