package com.zmen.backend.service;

import com.zmen.backend.dto.OrderRequest;
import com.zmen.backend.entity.*;
import com.zmen.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public Order createOrderFromCart(Cart cart, OrderRequest orderRequest) {
        if (cart.getTotalItems() == 0) {
            throw new RuntimeException("Cart is empty");
        }
        
        // Create order
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setCustomerPhone(orderRequest.getCustomerPhone());
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setBillingAddress(orderRequest.getBillingAddress());
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setVoucherCode(orderRequest.getVoucherCode());
        order.setNotes(orderRequest.getNotes());
        
        // Set totals from cart
        order.setTotalItems(cart.getTotalItems());
        order.setSubtotal(cart.getTotalAmount());
        order.setDiscountAmount(cart.getDiscountAmount());
        
        // Calculate shipping fee (simple logic)
        BigDecimal shippingFee = calculateShippingFee(cart.getTotalAmount());
        order.setShippingFee(shippingFee);
        
        // Calculate total amount
        BigDecimal totalAmount = cart.getTotalAmount()
            .subtract(cart.getDiscountAmount())
            .add(shippingFee);
        order.setTotalAmount(totalAmount);
        
        order = orderRepository.save(order);
        
        // Create order items from cart items
        var cartItems = cartItemRepository.findByCart(cart);
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(cartItem.getUnitPrice());
            orderItem.setSize(cartItem.getSize());
            orderItem.setColor(cartItem.getColor());
            
            orderItemRepository.save(orderItem);
            
            // Update product sold quantity
            Product product = cartItem.getProduct();
            product.setSoldQuantity(product.getSoldQuantity() + cartItem.getQuantity());
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);
        }
        
        // Clear cart
        cartItemRepository.deleteByCart(cart);
        cart.setTotalItems(0);
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setStatus("CHECKOUT");
        cartRepository.save(cart);
        
        return order;
    }
    
    public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            throw new RuntimeException("Order not found");
        }
        
        Order order = orderOpt.get();
        order.setStatus(status);
        
        if (status == Order.OrderStatus.SHIPPED) {
            order.setShippedAt(LocalDateTime.now());
        } else if (status == Order.OrderStatus.DELIVERED) {
            order.setDeliveredAt(LocalDateTime.now());
        }
        
        return orderRepository.save(order);
    }
    
    public Order updatePaymentStatus(Long orderId, Order.PaymentStatus paymentStatus, String paymentReference) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (!orderOpt.isPresent()) {
            throw new RuntimeException("Order not found");
        }
        
        Order order = orderOpt.get();
        order.setPaymentStatus(paymentStatus);
        order.setPaymentReference(paymentReference);
        
        if (paymentStatus == Order.PaymentStatus.PAID) {
            order.setStatus(Order.OrderStatus.CONFIRMED);
        }
        
        return orderRepository.save(order);
    }
    
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
    
    public Page<Order> getOrdersByStatus(Order.OrderStatus status, Pageable pageable) {
        return orderRepository.findByStatus(status, pageable);
    }
    
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }
    
    public Optional<Order> getOrderByNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }
    
    private BigDecimal calculateShippingFee(BigDecimal subtotal) {
        // Simple shipping fee calculation
        if (subtotal.compareTo(new BigDecimal("500000")) >= 0) {
            return BigDecimal.ZERO; // Free shipping for orders >= 500k
        }
        return new BigDecimal("30000"); // 30k shipping fee
    }
}

