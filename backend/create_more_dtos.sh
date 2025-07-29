#!/bin/bash

# Create MembershipInfoResponse.java
cat > src/main/java/com/zmen/backend/dto/MembershipInfoResponse.java << 'JAVA'
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
JAVA

# Create WishlistItemResponse.java
cat > src/main/java/com/zmen/backend/dto/WishlistItemResponse.java << 'JAVA'
package com.zmen.backend.dto;
import java.math.BigDecimal;
import java.util.Date;
public class WishlistItemResponse {
    private Long id;
    private Long productId;
    private String productName;
    private String productImage;
    private BigDecimal price;
    private Date addedAt;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Date getAddedAt() { return addedAt; }
    public void setAddedAt(Date addedAt) { this.addedAt = addedAt; }
}
JAVA

# Create WishlistResponse.java
cat > src/main/java/com/zmen/backend/dto/WishlistResponse.java << 'JAVA'
package com.zmen.backend.dto;
public class WishlistResponse {
    private String message;
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
JAVA

# Create NotificationResponse.java
cat > src/main/java/com/zmen/backend/dto/NotificationResponse.java << 'JAVA'
package com.zmen.backend.dto;
import java.util.Date;
public class NotificationResponse {
    private Long id;
    private String title;
    private String message;
    private String type;
    private Boolean isRead;
    private Date createdAt;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
JAVA

# Create DeleteAccountRequest.java
cat > src/main/java/com/zmen/backend/dto/DeleteAccountRequest.java << 'JAVA'
package com.zmen.backend.dto;
public class DeleteAccountRequest {
    private String confirmation;
    public String getConfirmation() { return confirmation; }
    public void setConfirmation(String confirmation) { this.confirmation = confirmation; }
}
JAVA

echo "More DTO files created successfully!"
