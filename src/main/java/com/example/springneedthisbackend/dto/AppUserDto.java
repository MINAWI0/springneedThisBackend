package com.example.springneedthisbackend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class AppUserDto {
    private Long id;
    private String fullName;
    private String email;
    private String image;
    private String location;
    private String website;
    private String birthDate;
    private String mobile;
    private String backgoundImage;
    private String bio;
    private boolean req_user;
    private boolean login_with_google;
    private List<AppUserDto> followers=new ArrayList<>();
    private List<AppUserDto> following=new ArrayList<>();
    private boolean followed;
    private boolean isSeller;
}
