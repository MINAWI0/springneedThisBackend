package com.example.springneedthisbackend.repo;

import com.example.springneedthisbackend.model.AppUser;
import com.example.springneedthisbackend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findAllByRequestTypeTrueOrderByCreatedAtDesc();
    List<Request> findByReRequestUserContainsOrAppUser_IdAndRequestTypeTrueOrderByCreatedAtDesc(AppUser appUser , Long appUserId);
    List<Request> findByLikesContainingOrderByCreatedAtDesc(AppUser appUser);
    List<Request> findAllRequestsByAppUserOrderByCreatedAtDesc(AppUser appUser);
    @Query("select r from Request r join r.likes l where l.user.id=:userId")
    List<Request> findByLikesAppUser_Id(Long userId);
    @Query("SELECT r FROM Request r LEFT JOIN FETCH r.replyRequests WHERE r.appUser.id = :userId AND r.closed = true")
    List<Request> findAllClosedRequestsAndRepliesByUserId(@Param("userId") Long userId);
}
