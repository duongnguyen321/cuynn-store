package com.zmen.backend.repository;

import com.zmen.backend.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    
    List<PaymentTransaction> findByOrderIdOrderByCreatedAtDesc(Long orderId);
    
    List<PaymentTransaction> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    List<PaymentTransaction> findByStatusOrderByCreatedAtDesc(String status);
    
    Optional<PaymentTransaction> findByTransactionIdAndUserId(String transactionId, Long userId);
    
    @Query("SELECT pt FROM PaymentTransaction pt WHERE pt.createdAt BETWEEN :startDate AND :endDate")
    List<PaymentTransaction> findByDateRange(@Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT pt FROM PaymentTransaction pt WHERE pt.paymentMethod = :method AND pt.status = :status")
    List<PaymentTransaction> findByPaymentMethodAndStatus(@Param("method") String method, 
                                                         @Param("status") String status);
}