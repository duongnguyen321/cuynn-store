#!/bin/bash

# Create DTO directory
mkdir -p src/main/java/com/zmen/backend/dto

# Create UpdateUserProfileRequest.java
cat > src/main/java/com/zmen/backend/dto/UpdateUserProfileRequest.java << 'JAVA'
package com.zmen.backend.dto;
import java.util.Date;
public class UpdateUserProfileRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private Date dateOfBirth;
    private String gender;
    private String avatar;
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
}
JAVA

# Create UploadAvatarRequest.java
cat > src/main/java/com/zmen/backend/dto/UploadAvatarRequest.java << 'JAVA'
package com.zmen.backend.dto;
public class UploadAvatarRequest {
    private String avatarUrl;
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}
JAVA

# Create ChangePasswordRequest.java
cat > src/main/java/com/zmen/backend/dto/ChangePasswordRequest.java << 'JAVA'
package com.zmen.backend.dto;
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
    public String getCurrentPassword() { return currentPassword; }
    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }
    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
JAVA

# Create LoyaltyPointResponse.java
cat > src/main/java/com/zmen/backend/dto/LoyaltyPointResponse.java << 'JAVA'
package com.zmen.backend.dto;
import java.util.Date;
public class LoyaltyPointResponse {
    private Long id;
    private Integer points;
    private String type;
    private String description;
    private Date createdAt;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
JAVA

echo "DTO files created successfully!"
