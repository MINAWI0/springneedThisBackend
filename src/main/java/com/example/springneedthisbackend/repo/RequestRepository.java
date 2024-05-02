package com.example.springneedthisbackend.repo;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findAllByRequestTypeTrueOrderByCreatedAtDesc();
    List<Request> findByReRequestUserContainsOrAppUser_IdAndRequestTypeTrueOrderByCreatedAtDesc(AppUser appUser , Long appUserId);
    List<Request> findByLikesContainingOrderByCreatedAtDesc(AppUser appUser);
    @Query("select r from Request r join r.likes l where l.user.id=:userId")
    List<Request> findByLikesAppUser_Id(Long userId);
}
