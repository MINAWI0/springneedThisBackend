package com.example.springneedthisbackend.dto;


import lombok.Data;

@Data
public class LikeDto {
    private Long id;
    private AppUserDto user;
    private RequestDto request;

}
