package com.zmen.backend.dto;

import org.springframework.data.domain.Page;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VoucherUsageResponse {
    private Long voucherId;
    private String voucherCode;
    private String voucherTitle;
    
    // Usage statistics
    private Integer totalUsageCount;
    private Integer initialQuantity;
    private Integer remainingQuantity;
    private Double usageRate;
    private BigDecimal totalDiscountAmount;
    private BigDecimal averageDiscountAmount;
    private BigDecimal averageOrderAmount;
    
    // Time-based statistics
    private LocalDateTime firstUsageDate;
    private LocalDateTime lastUsageDate;
    private LocalDateTime createdAt;
    private LocalDateTime voucherStartDate;
    private LocalDateTime voucherEndDate;
    
    // Usage details (paginated)
    private Page<VoucherUsageDetail> usageHistory;
    private List<VoucherUsageDetail> recentUsage;
    
    // Performance metrics
    private VoucherPerformanceMetrics performance;
    
    // Constructors
    public VoucherUsageResponse() {}
    
    // Getters and Setters
    public Long getVoucherId() { return voucherId; }
    public void setVoucherId(Long voucherId) { this.voucherId = voucherId; }
    
    public String getVoucherCode() { return voucherCode; }
    public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
    
    public String getVoucherTitle() { return voucherTitle; }
    public void setVoucherTitle(String voucherTitle) { this.voucherTitle = voucherTitle; }
    
    public Integer getTotalUsageCount() { return totalUsageCount; }
    public void setTotalUsageCount(Integer totalUsageCount) { this.totalUsageCount = totalUsageCount; }
    
    public Integer getInitialQuantity() { return initialQuantity; }
    public void setInitialQuantity(Integer initialQuantity) { this.initialQuantity = initialQuantity; }
    
    public Integer getRemainingQuantity() { return remainingQuantity; }
    public void setRemainingQuantity(Integer remainingQuantity) { this.remainingQuantity = remainingQuantity; }
    
    public Double getUsageRate() { return usageRate; }
    public void setUsageRate(Double usageRate) { this.usageRate = usageRate; }
    
    public BigDecimal getTotalDiscountAmount() { return totalDiscountAmount; }
    public void setTotalDiscountAmount(BigDecimal totalDiscountAmount) { this.totalDiscountAmount = totalDiscountAmount; }
    
    public BigDecimal getAverageDiscountAmount() { return averageDiscountAmount; }
    public void setAverageDiscountAmount(BigDecimal averageDiscountAmount) { this.averageDiscountAmount = averageDiscountAmount; }
    
    public BigDecimal getAverageOrderAmount() { return averageOrderAmount; }
    public void setAverageOrderAmount(BigDecimal averageOrderAmount) { this.averageOrderAmount = averageOrderAmount; }
    
    public LocalDateTime getFirstUsageDate() { return firstUsageDate; }
    public void setFirstUsageDate(LocalDateTime firstUsageDate) { this.firstUsageDate = firstUsageDate; }
    
    public LocalDateTime getLastUsageDate() { return lastUsageDate; }
    public void setLastUsageDate(LocalDateTime lastUsageDate) { this.lastUsageDate = lastUsageDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getVoucherStartDate() { return voucherStartDate; }
    public void setVoucherStartDate(LocalDateTime voucherStartDate) { this.voucherStartDate = voucherStartDate; }
    
    public LocalDateTime getVoucherEndDate() { return voucherEndDate; }
    public void setVoucherEndDate(LocalDateTime voucherEndDate) { this.voucherEndDate = voucherEndDate; }
    
    public Page<VoucherUsageDetail> getUsageHistory() { return usageHistory; }
    public void setUsageHistory(Page<VoucherUsageDetail> usageHistory) { this.usageHistory = usageHistory; }
    
    public List<VoucherUsageDetail> getRecentUsage() { return recentUsage; }
    public void setRecentUsage(List<VoucherUsageDetail> recentUsage) { this.recentUsage = recentUsage; }
    
    public VoucherPerformanceMetrics getPerformance() { return performance; }
    public void setPerformance(VoucherPerformanceMetrics performance) { this.performance = performance; }
    
    // Inner class for individual usage details
    public static class VoucherUsageDetail {
        private Long id;
        private String userEmail;
        private String userName;
        private Long orderId;
        private BigDecimal orderAmount;
        private BigDecimal discountAmount;
        private BigDecimal finalAmount;
        private LocalDateTime usedAt;
        private String orderStatus;
        private String paymentMethod;
        
        public VoucherUsageDetail() {}
        
        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        
        public String getUserEmail() { return userEmail; }
        public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
        
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        
        public Long getOrderId() { return orderId; }
        public void setOrderId(Long orderId) { this.orderId = orderId; }
        
        public BigDecimal getOrderAmount() { return orderAmount; }
        public void setOrderAmount(BigDecimal orderAmount) { this.orderAmount = orderAmount; }
        
        public BigDecimal getDiscountAmount() { return discountAmount; }
        public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
        
        public BigDecimal getFinalAmount() { return finalAmount; }
        public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
        
        public LocalDateTime getUsedAt() { return usedAt; }
        public void setUsedAt(LocalDateTime usedAt) { this.usedAt = usedAt; }
        
        public String getOrderStatus() { return orderStatus; }
        public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
        
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    }
    
    // Inner class for performance metrics
    public static class VoucherPerformanceMetrics {
        private Double conversionRate;
        private BigDecimal averageOrderValue;
        private BigDecimal totalRevenue;
        private Integer uniqueUsers;
        private Integer repeatUsers;
        private Double customerRetentionRate;
        private Integer peakUsageHour;
        private String mostPopularDay;
        
        public VoucherPerformanceMetrics() {}
        
        // Getters and Setters
        public Double getConversionRate() { return conversionRate; }
        public void setConversionRate(Double conversionRate) { this.conversionRate = conversionRate; }
        
        public BigDecimal getAverageOrderValue() { return averageOrderValue; }
        public void setAverageOrderValue(BigDecimal averageOrderValue) { this.averageOrderValue = averageOrderValue; }
        
        public BigDecimal getTotalRevenue() { return totalRevenue; }
        public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
        
        public Integer getUniqueUsers() { return uniqueUsers; }
        public void setUniqueUsers(Integer uniqueUsers) { this.uniqueUsers = uniqueUsers; }
        
        public Integer getRepeatUsers() { return repeatUsers; }
        public void setRepeatUsers(Integer repeatUsers) { this.repeatUsers = repeatUsers; }
        
        public Double getCustomerRetentionRate() { return customerRetentionRate; }
        public void setCustomerRetentionRate(Double customerRetentionRate) { this.customerRetentionRate = customerRetentionRate; }
        
        public Integer getPeakUsageHour() { return peakUsageHour; }
        public void setPeakUsageHour(Integer peakUsageHour) { this.peakUsageHour = peakUsageHour; }
        
        public String getMostPopularDay() { return mostPopularDay; }
        public void setMostPopularDay(String mostPopularDay) { this.mostPopularDay = mostPopularDay; }
    }
}