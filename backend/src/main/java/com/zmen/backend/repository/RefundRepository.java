package com.zmen.backend.repository;

import com.zmen.backend.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
    
    List<Refund> findByOrderIdOrderByCreatedAtDesc(Long orderId);
    
    List<Refund> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    List<Refund> findByStatusOrderByCreatedAtDesc(String status);
    
    Optional<Refund> findByRefundIdAndUserId(String refundId, Long userId);
    
    @Query("SELECT r FROM Refund r WHERE r.createdAt BETWEEN :startDate AND :endDate")
    List<Refund> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT r FROM Refund r WHERE r.paymentTransaction.id = :transactionId")
    List<Refund> findByPaymentTransactionId(@Param("transactionId") Long transactionId);
}