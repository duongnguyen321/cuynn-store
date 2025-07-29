package com.zmen.backend.dto;
public class MembershipInfoResponse {
    private String membershipLevel;
    private Long totalPoints;
    private Long pointsToNextLevel;
    public String getMembershipLevel() { return membershipLevel; }
    public void setMembershipLevel(String membershipLevel) { this.membershipLevel = membershipLevel; }
    public Long getTotalPoints() { return totalPoints; }
    public void setTotalPoints(Long totalPoints) { this.totalPoints = totalPoints; }
    public Long getPointsToNextLevel() { return pointsToNextLevel; }
    public void setPointsToNextLevel(Long pointsToNextLevel) { this.pointsToNextLevel = pointsToNextLevel; }
}
