package com.zmen.backend.repository;

import com.zmen.backend.entity.User;
import com.zmen.backend.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    
    List<UserAddress> findByUserOrderByIsDefaultDescCreatedAtDesc(User user);
    
    Optional<UserAddress> findByUserAndIsDefaultTrue(User user);
    
    @Query("SELECT ua FROM UserAddress ua WHERE ua.user.id = :userId ORDER BY ua.isDefault DESC")
    List<UserAddress> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT ua FROM UserAddress ua WHERE ua.user.id = :userId AND ua.isDefault = true")
    Optional<UserAddress> findDefaultAddressByUserId(@Param("userId") Long userId);
}