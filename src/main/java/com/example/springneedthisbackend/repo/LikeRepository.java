package com.example.springneedthisbackend.repo;

import com.example.springneedthisbackend.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query("SELECT l from Like l where l.user.id=:userId and l.request.id=:requestId")
    public Like isLikeExiste(@Param("userId") Long userId , @Param("requestId") Long requestId);

    @Query("select l from Like l where l.request.id=:requestId")
    public List<Like> findByRequestId(@Param("requestId") Long requestId);

}
