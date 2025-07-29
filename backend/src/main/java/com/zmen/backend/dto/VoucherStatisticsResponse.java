package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class VoucherStatisticsResponse {
    
    // Time period for the statistics
    private LocalDate startDate;
    private LocalDate endDate;
    private String period;
    
    // Overall voucher statistics
    private VoucherOverallStats overallStats;
    
    // Usage statistics
    private VoucherUsageStats usageStats;
    
    // Performance statistics
    private VoucherPerformanceStats performanceStats;
    
    // Time-based statistics
    private List<VoucherDailyStats> dailyStats;
    private Map<String, VoucherTypeStats> typeStats;
    
    // Top performing vouchers
    private List<TopVoucherStats> topVouchersByUsage;
    private List<TopVoucherStats> topVouchersByRevenue;
    
    // User behavior statistics
    private VoucherUserBehaviorStats userBehaviorStats;
    
    // Constructors
    public VoucherStatisticsResponse() {}
    
    // Getters and Setters
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    
    public VoucherOverallStats getOverallStats() { return overallStats; }
    public void setOverallStats(VoucherOverallStats overallStats) { this.overallStats = overallStats; }
    
    public VoucherUsageStats getUsageStats() { return usageStats; }
    public void setUsageStats(VoucherUsageStats usageStats) { this.usageStats = usageStats; }
    
    public VoucherPerformanceStats getPerformanceStats() { return performanceStats; }
    public void setPerformanceStats(VoucherPerformanceStats performanceStats) { this.performanceStats = performanceStats; }
    
    public List<VoucherDailyStats> getDailyStats() { return dailyStats; }
    public void setDailyStats(List<VoucherDailyStats> dailyStats) { this.dailyStats = dailyStats; }
    
    public Map<String, VoucherTypeStats> getTypeStats() { return typeStats; }
    public void setTypeStats(Map<String, VoucherTypeStats> typeStats) { this.typeStats = typeStats; }
    
    public List<TopVoucherStats> getTopVouchersByUsage() { return topVouchersByUsage; }
    public void setTopVouchersByUsage(List<TopVoucherStats> topVouchersByUsage) { this.topVouchersByUsage = topVouchersByUsage; }
    
    public List<TopVoucherStats> getTopVouchersByRevenue() { return topVouchersByRevenue; }
    public void setTopVouchersByRevenue(List<TopVoucherStats> topVouchersByRevenue) { this.topVouchersByRevenue = topVouchersByRevenue; }
    
    public VoucherUserBehaviorStats getUserBehaviorStats() { return userBehaviorStats; }
    public void setUserBehaviorStats(VoucherUserBehaviorStats userBehaviorStats) { this.userBehaviorStats = userBehaviorStats; }
    
    // Inner classes for different statistics categories
    
    public static class VoucherOverallStats {
        private Integer totalVouchers;
        private Integer activeVouchers;
        private Integer expiredVouchers;
        private Integer fullyUsedVouchers;
        private BigDecimal totalDiscountValue;
        private BigDecimal averageDiscountValue;
        
        public VoucherOverallStats() {}
        
        public Integer getTotalVouchers() { return totalVouchers; }
        public void setTotalVouchers(Integer totalVouchers) { this.totalVouchers = totalVouchers; }
        
        public Integer getActiveVouchers() { return activeVouchers; }
        public void setActiveVouchers(Integer activeVouchers) { this.activeVouchers = activeVouchers; }
        
        public Integer getExpiredVouchers() { return expiredVouchers; }
        public void setExpiredVouchers(Integer expiredVouchers) { this.expiredVouchers = expiredVouchers; }
        
        public Integer getFullyUsedVouchers() { return fullyUsedVouchers; }
        public void setFullyUsedVouchers(Integer fullyUsedVouchers) { this.fullyUsedVouchers = fullyUsedVouchers; }
        
        public BigDecimal getTotalDiscountValue() { return totalDiscountValue; }
        public void setTotalDiscountValue(BigDecimal totalDiscountValue) { this.totalDiscountValue = totalDiscountValue; }
        
        public BigDecimal getAverageDiscountValue() { return averageDiscountValue; }
        public void setAverageDiscountValue(BigDecimal averageDiscountValue) { this.averageDiscountValue = averageDiscountValue; }
    }
    
    public static class VoucherUsageStats {
        private Integer totalUsages;
        private Integer uniqueUsers;
        private Double overallUsageRate;
        private BigDecimal totalDiscountGiven;
        private BigDecimal averageDiscountPerUse;
        private BigDecimal totalOrderValue;
        private BigDecimal averageOrderValue;
        
        public VoucherUsageStats() {}
        
        public Integer getTotalUsages() { return totalUsages; }
        public void setTotalUsages(Integer totalUsages) { this.totalUsages = totalUsages; }
        
        public Integer getUniqueUsers() { return uniqueUsers; }
        public void setUniqueUsers(Integer uniqueUsers) { this.uniqueUsers = uniqueUsers; }
        
        public Double getOverallUsageRate() { return overallUsageRate; }
        public void setOverallUsageRate(Double overallUsageRate) { this.overallUsageRate = overallUsageRate; }
        
        public BigDecimal getTotalDiscountGiven() { return totalDiscountGiven; }
        public void setTotalDiscountGiven(BigDecimal totalDiscountGiven) { this.totalDiscountGiven = totalDiscountGiven; }
        
        public BigDecimal getAverageDiscountPerUse() { return averageDiscountPerUse; }
        public void setAverageDiscountPerUse(BigDecimal averageDiscountPerUse) { this.averageDiscountPerUse = averageDiscountPerUse; }
        
        public BigDecimal getTotalOrderValue() { return totalOrderValue; }
        public void setTotalOrderValue(BigDecimal totalOrderValue) { this.totalOrderValue = totalOrderValue; }
        
        public BigDecimal getAverageOrderValue() { return averageOrderValue; }
        public void setAverageOrderValue(BigDecimal averageOrderValue) { this.averageOrderValue = averageOrderValue; }
    }
    
    public static class VoucherPerformanceStats {
        private Double conversionRate;
        private BigDecimal revenueGenerated;
        private BigDecimal costOfDiscounts;
        private BigDecimal roi;
        private Double customerAcquisitionRate;
        private Double customerRetentionRate;
        private Integer repeatUsageCount;
        
        public VoucherPerformanceStats() {}
        
        public Double getConversionRate() { return conversionRate; }
        public void setConversionRate(Double conversionRate) { this.conversionRate = conversionRate; }
        
        public BigDecimal getRevenueGenerated() { return revenueGenerated; }
        public void setRevenueGenerated(BigDecimal revenueGenerated) { this.revenueGenerated = revenueGenerated; }
        
        public BigDecimal getCostOfDiscounts() { return costOfDiscounts; }
        public void setCostOfDiscounts(BigDecimal costOfDiscounts) { this.costOfDiscounts = costOfDiscounts; }
        
        public BigDecimal getRoi() { return roi; }
        public void setRoi(BigDecimal roi) { this.roi = roi; }
        
        public Double getCustomerAcquisitionRate() { return customerAcquisitionRate; }
        public void setCustomerAcquisitionRate(Double customerAcquisitionRate) { this.customerAcquisitionRate = customerAcquisitionRate; }
        
        public Double getCustomerRetentionRate() { return customerRetentionRate; }
        public void setCustomerRetentionRate(Double customerRetentionRate) { this.customerRetentionRate = customerRetentionRate; }
        
        public Integer getRepeatUsageCount() { return repeatUsageCount; }
        public void setRepeatUsageCount(Integer repeatUsageCount) { this.repeatUsageCount = repeatUsageCount; }
    }
    
    public static class VoucherDailyStats {
        private LocalDate date;
        private Integer usageCount;
        private BigDecimal discountAmount;
        private Integer uniqueUsers;
        private BigDecimal orderValue;
        
        public VoucherDailyStats() {}
        
        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
        
        public Integer getUsageCount() { return usageCount; }
        public void setUsageCount(Integer usageCount) { this.usageCount = usageCount; }
        
        public BigDecimal getDiscountAmount() { return discountAmount; }
        public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
        
        public Integer getUniqueUsers() { return uniqueUsers; }
        public void setUniqueUsers(Integer uniqueUsers) { this.uniqueUsers = uniqueUsers; }
        
        public BigDecimal getOrderValue() { return orderValue; }
        public void setOrderValue(BigDecimal orderValue) { this.orderValue = orderValue; }
    }
    
    public static class VoucherTypeStats {
        private String discountType;
        private Integer count;
        private Integer usageCount;
        private BigDecimal totalDiscountGiven;
        private Double averageUsageRate;
        
        public VoucherTypeStats() {}
        
        public String getDiscountType() { return discountType; }
        public void setDiscountType(String discountType) { this.discountType = discountType; }
        
        public Integer getCount() { return count; }
        public void setCount(Integer count) { this.count = count; }
        
        public Integer getUsageCount() { return usageCount; }
        public void setUsageCount(Integer usageCount) { this.usageCount = usageCount; }
        
        public BigDecimal getTotalDiscountGiven() { return totalDiscountGiven; }
        public void setTotalDiscountGiven(BigDecimal totalDiscountGiven) { this.totalDiscountGiven = totalDiscountGiven; }
        
        public Double getAverageUsageRate() { return averageUsageRate; }
        public void setAverageUsageRate(Double averageUsageRate) { this.averageUsageRate = averageUsageRate; }
    }
    
    public static class TopVoucherStats {
        private Long voucherId;
        private String voucherCode;
        private String title;
        private Integer usageCount;
        private BigDecimal totalDiscountGiven;
        private BigDecimal totalRevenue;
        private Double usageRate;
        
        public TopVoucherStats() {}
        
        public Long getVoucherId() { return voucherId; }
        public void setVoucherId(Long voucherId) { this.voucherId = voucherId; }
        
        public String getVoucherCode() { return voucherCode; }
        public void setVoucherCode(String voucherCode) { this.voucherCode = voucherCode; }
        
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        
        public Integer getUsageCount() { return usageCount; }
        public void setUsageCount(Integer usageCount) { this.usageCount = usageCount; }
        
        public BigDecimal getTotalDiscountGiven() { return totalDiscountGiven; }
        public void setTotalDiscountGiven(BigDecimal totalDiscountGiven) { this.totalDiscountGiven = totalDiscountGiven; }
        
        public BigDecimal getTotalRevenue() { return totalRevenue; }
        public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
        
        public Double getUsageRate() { return usageRate; }
        public void setUsageRate(Double usageRate) { this.usageRate = usageRate; }
    }
    
    public static class VoucherUserBehaviorStats {
        private Integer newCustomers;
        private Integer returningCustomers;
        private Double newCustomerRate;
        private BigDecimal averageNewCustomerOrderValue;
        private BigDecimal averageReturningCustomerOrderValue;
        private Integer averageVouchersPerCustomer;
        private Double customerSatisfactionScore;
        
        public VoucherUserBehaviorStats() {}
        
        public Integer getNewCustomers() { return newCustomers; }
        public void setNewCustomers(Integer newCustomers) { this.newCustomers = newCustomers; }
        
        public Integer getReturningCustomers() { return returningCustomers; }
        public void setReturningCustomers(Integer returningCustomers) { this.returningCustomers = returningCustomers; }
        
        public Double getNewCustomerRate() { return newCustomerRate; }
        public void setNewCustomerRate(Double newCustomerRate) { this.newCustomerRate = newCustomerRate; }
        
        public BigDecimal getAverageNewCustomerOrderValue() { return averageNewCustomerOrderValue; }
        public void setAverageNewCustomerOrderValue(BigDecimal averageNewCustomerOrderValue) { this.averageNewCustomerOrderValue = averageNewCustomerOrderValue; }
        
        public BigDecimal getAverageReturningCustomerOrderValue() { return averageReturningCustomerOrderValue; }
        public void setAverageReturningCustomerOrderValue(BigDecimal averageReturningCustomerOrderValue) { this.averageReturningCustomerOrderValue = averageReturningCustomerOrderValue; }
        
        public Integer getAverageVouchersPerCustomer() { return averageVouchersPerCustomer; }
        public void setAverageVouchersPerCustomer(Integer averageVouchersPerCustomer) { this.averageVouchersPerCustomer = averageVouchersPerCustomer; }
        
        public Double getCustomerSatisfactionScore() { return customerSatisfactionScore; }
        public void setCustomerSatisfactionScore(Double customerSatisfactionScore) { this.customerSatisfactionScore = customerSatisfactionScore; }
    }
}