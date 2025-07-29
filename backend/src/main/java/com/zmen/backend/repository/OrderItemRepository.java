package com.zmen.backend.repository;

import com.zmen.backend.entity.Order;
import com.zmen.backend.entity.OrderItem;
import com.zmen.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
    List<OrderItem> findByOrder(Order order);
    
    List<OrderItem> findByProduct(Product product);
    
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.status = 'DELIVERED'")
    List<OrderItem> findDeliveredOrderItems();
    
    @Query("SELECT oi.product, SUM(oi.quantity) as totalSold FROM OrderItem oi " +
           "WHERE oi.order.status = 'DELIVERED' " +
           "GROUP BY oi.product ORDER BY totalSold DESC")
    List<Object[]> findBestSellingProducts();
    
    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.product = :product AND oi.order.status = 'DELIVERED'")
    Integer getTotalSoldQuantityByProduct(@Param("product") Product product);
}

