package com.zmen.backend.repository;

import com.zmen.backend.entity.User;
import com.zmen.backend.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    
    Optional<UserProfile> findByUser(User user);
    
    Optional<UserProfile> findByPhoneNumber(String phoneNumber);
    
    @Query("SELECT up FROM UserProfile up WHERE up.user.id = :userId")
    Optional<UserProfile> findByUserId(@Param("userId") Long userId);
    
    boolean existsByPhoneNumber(String phoneNumber);
}