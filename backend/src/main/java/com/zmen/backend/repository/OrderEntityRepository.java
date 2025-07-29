package com.zmen.backend.repository;

import com.zmen.backend.entity.OrderEntity;
import com.zmen.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
    
    Optional<OrderEntity> findByOrderCode(String orderCode);
    
    List<OrderEntity> findByUserOrderByCreatedAtDesc(User user);
    
    List<OrderEntity> findByOrderStatusOrderByCreatedAtDesc(OrderEntity.OrderStatus orderStatus);
    
    List<OrderEntity> findByPaymentStatusOrderByCreatedAtDesc(OrderEntity.PaymentStatus paymentStatus);
    
    @Query("SELECT o FROM OrderEntity o WHERE o.user.id = :userId ORDER BY o.createdAt DESC")
    List<OrderEntity> findByUserId(@Param("userId") Long userId);
    
    @Query("SELECT o FROM OrderEntity o WHERE o.user.id = :userId AND o.orderStatus = :status ORDER BY o.createdAt DESC")
    List<OrderEntity> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") OrderEntity.OrderStatus status);
    
    @Query("SELECT o FROM OrderEntity o WHERE o.orderDate BETWEEN :startDate AND :endDate ORDER BY o.orderDate DESC")
    List<OrderEntity> findOrdersByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(o) FROM OrderEntity o WHERE o.orderStatus = :status")
    Long countByOrderStatus(@Param("status") OrderEntity.OrderStatus status);
    
    @Query("SELECT SUM(o.finalAmount) FROM OrderEntity o WHERE o.orderStatus = :status AND o.paymentStatus = :paymentStatus")
    Double getTotalRevenueByStatusAndPayment(@Param("status") OrderEntity.OrderStatus status, @Param("paymentStatus") OrderEntity.PaymentStatus paymentStatus);
    
    boolean existsByOrderCode(String orderCode);
}