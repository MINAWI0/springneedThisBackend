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
    private String facebook; // Facebook属性
    private String twitter; // Twitter属性
    private String instagram; // Instagram属性
    private String phoneNumber; // 电话号码属性
    private String professionalEmail; // 专业电子邮件属性
    private String termsAndService; // 服务条款属性
    private String companyName;
}
