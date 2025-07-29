package com.zmen.backend.repository;

import com.zmen.backend.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    
    Optional<UserProfile> findByUserId(Long userId);
    
    @Modifying
    @Query("DELETE FROM UserProfile up WHERE up.userId = :userId")
    void deleteByUserId(@Param("userId") Long userId);
    
    boolean existsByUserId(Long userId);
}

