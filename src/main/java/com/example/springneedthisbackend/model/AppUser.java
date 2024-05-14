package com.example.springneedthisbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String location;
    private String website;
    private String birthDate;
    private String email;
    private String password;
    private String mobile;
    private String image;
    private String backgroundImage;
    private String bio;
    private boolean isSeller;
    private boolean req_user;
    private boolean login_with_google;
    @JsonIgnore
    @OneToMany(mappedBy = "appUser",cascade= CascadeType.ALL)
    private List<Request> request =new ArrayList<>();

    @OneToMany
    private List<Like> likes = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<AppUser> followers = new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    private List<AppUser> followings = new ArrayList<>();

    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", website='" + website + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", image='" + image + '\'' +
                ", backgroundImage='" + backgroundImage + '\'' +
                ", bio='" + bio + '\'' +
                ", isSeller=" + isSeller +
                ", req_user=" + req_user +
                ", login_with_google=" + login_with_google +
                '}';
    }
}
