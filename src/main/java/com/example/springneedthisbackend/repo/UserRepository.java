package com.example.springneedthisbackend.repo;

import com.example.springneedthisbackend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {

    public AppUser findUsersByEmail(String email);

    @Query("select distinct u from AppUser u where u.fullName like %:query%  or u.email like %:query%")
public List<AppUser> searchUserBy(@Param("query") String query);

}