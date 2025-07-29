package com.zmen.backend.dto;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentStatisticsResponse {
    private BigDecimal totalRevenue;
    private BigDecimal totalRefunds;
    private BigDecimal netRevenue;
    private Integer totalTransactions;
    private Integer successfulTransactions;
    private Integer failedTransactions;
    private Integer pendingTransactions;
    private Integer totalRefundRequests;
    private Double successRate;
    private BigDecimal averageTransactionAmount;
    private BigDecimal totalProcessingFees;
    private Map<String, Integer> transactionsByMethod;
    private Map<String, BigDecimal> revenueByMethod;
    private Map<String, Integer> transactionsByStatus;
    private Map<String, Double> successRateByMethod;
    
    // Constructors
    public PaymentStatisticsResponse() {}
    
    // Getters and Setters
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public BigDecimal getTotalRefunds() { return totalRefunds; }
    public void setTotalRefunds(BigDecimal totalRefunds) { this.totalRefunds = totalRefunds; }
    
    public BigDecimal getNetRevenue() { return netRevenue; }
    public void setNetRevenue(BigDecimal netRevenue) { this.netRevenue = netRevenue; }
    
    public Integer getTotalTransactions() { return totalTransactions; }
    public void setTotalTransactions(Integer totalTransactions) { this.totalTransactions = totalTransactions; }
    
    public Integer getSuccessfulTransactions() { return successfulTransactions; }
    public void setSuccessfulTransactions(Integer successfulTransactions) { this.successfulTransactions = successfulTransactions; }
    
    public Integer getFailedTransactions() { return failedTransactions; }
    public void setFailedTransactions(Integer failedTransactions) { this.failedTransactions = failedTransactions; }
    
    public Integer getPendingTransactions() { return pendingTransactions; }
    public void setPendingTransactions(Integer pendingTransactions) { this.pendingTransactions = pendingTransactions; }
    
    public Integer getTotalRefundRequests() { return totalRefundRequests; }
    public void setTotalRefundRequests(Integer totalRefundRequests) { this.totalRefundRequests = totalRefundRequests; }
    
    public Double getSuccessRate() { return successRate; }
    public void setSuccessRate(Double successRate) { this.successRate = successRate; }
    
    public BigDecimal getAverageTransactionAmount() { return averageTransactionAmount; }
    public void setAverageTransactionAmount(BigDecimal averageTransactionAmount) { this.averageTransactionAmount = averageTransactionAmount; }
    
    public BigDecimal getTotalProcessingFees() { return totalProcessingFees; }
    public void setTotalProcessingFees(BigDecimal totalProcessingFees) { this.totalProcessingFees = totalProcessingFees; }
    
    public Map<String, Integer> getTransactionsByMethod() { return transactionsByMethod; }
    public void setTransactionsByMethod(Map<String, Integer> transactionsByMethod) { this.transactionsByMethod = transactionsByMethod; }
    
    public Map<String, BigDecimal> getRevenueByMethod() { return revenueByMethod; }
    public void setRevenueByMethod(Map<String, BigDecimal> revenueByMethod) { this.revenueByMethod = revenueByMethod; }
    
    public Map<String, Integer> getTransactionsByStatus() { return transactionsByStatus; }
    public void setTransactionsByStatus(Map<String, Integer> transactionsByStatus) { this.transactionsByStatus = transactionsByStatus; }
    
    public Map<String, Double> getSuccessRateByMethod() { return successRateByMethod; }
    public void setSuccessRateByMethod(Map<String, Double> successRateByMethod) { this.successRateByMethod = successRateByMethod; }
}