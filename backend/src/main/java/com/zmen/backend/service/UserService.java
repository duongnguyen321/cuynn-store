package com.zmen.backend.service;

import com.zmen.backend.entity.User;
import com.zmen.backend.entity.UserProfile;
import com.zmen.backend.entity.Wishlist;
import com.zmen.backend.entity.Notification;
import com.zmen.backend.repository.UserRepository;
import com.zmen.backend.repository.UserProfileRepository;
import com.zmen.backend.repository.WishlistRepository;
import com.zmen.backend.repository.NotificationRepository;
import com.zmen.backend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    @Autowired
    private WishlistRepository wishlistRepository;
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("No authenticated user found");
        }
        String email = authentication.getName();
        
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Current user not found"));
    }
    
    public UserProfile getUserProfile(Long userId) {
        return userProfileRepository.findByUserId(userId)
            .orElse(null);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
    
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    public UserProfileResponse getUserProfile(String email) {
        User user = findByEmail(email);
        UserProfile profile = getUserProfile(user.getId());
        
        UserProfileResponse response = new UserProfileResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setEmailVerified(user.getEmailVerified());
        response.setCreatedAt(user.getCreatedAt());
        
        if (profile != null) {
            response.setDateOfBirth(profile.getDateOfBirth());
            response.setGender(profile.getGender());
            response.setAvatar(profile.getAvatar());
        }
        
        return response;
    }
    
    public UserProfileResponse updateUserProfile(String email, UpdateUserProfileRequest request) {
        User user = findByEmail(email);
        
        // Update user basic info
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        
        userRepository.save(user);
        
        // Update or create user profile
        UserProfile profile = userProfileRepository.findByUserId(user.getId()).orElse(new UserProfile());
        profile.setUserId(user.getId());
        
        if (request.getDateOfBirth() != null) {
            profile.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getGender() != null) {
            profile.setGender(request.getGender());
        }
        if (request.getAvatar() != null) {
            profile.setAvatar(request.getAvatar());
        }
        
        userProfileRepository.save(profile);
        
        return getUserProfile(email);
    }
    
    public UserProfileResponse uploadAvatar(String email, UploadAvatarRequest request) {
        User user = findByEmail(email);
        
        // Update or create user profile
        UserProfile profile = userProfileRepository.findByUserId(user.getId()).orElse(new UserProfile());
        profile.setUserId(user.getId());
        profile.setAvatar(request.getAvatarUrl());
        
        userProfileRepository.save(profile);
        
        return getUserProfile(email);
    }
    
    public void changePassword(String email, ChangePasswordRequest request) {
        User user = findByEmail(email);
        
        // Verify current password (simplified implementation)
        // In real implementation, you should verify the current password
        
        // Update password (should be encoded)
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }
    
    public Page<LoyaltyPointResponse> getLoyaltyPoints(String email, int page, int size) {
        User user = findByEmail(email);
        Pageable pageable = PageRequest.of(page, size);
        
        // Simplified implementation - return empty page
        return Page.empty(pageable);
    }
    
    public MembershipInfoResponse getMembershipInfo(String email) {
        User user = findByEmail(email);
        
        MembershipInfoResponse response = new MembershipInfoResponse();
        response.setMembershipLevel("BRONZE");
        response.setTotalPoints(0L);
        response.setPointsToNextLevel(1000L);
        
        return response;
    }
    
    public Page<WishlistItemResponse> getWishlist(String email, int page, int size) {
        User user = findByEmail(email);
        Pageable pageable = PageRequest.of(page, size);
        
        // Simplified implementation - return empty page
        return Page.empty(pageable);
    }
    
    public WishlistResponse addToWishlist(String email, Long productId) {
        User user = findByEmail(email);
        
        // Check if already in wishlist
        if (wishlistRepository.findByUserIdAndProductId(user.getId(), productId).isPresent()) {
            throw new RuntimeException("Product already in wishlist");
        }
        
        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(user.getId());
        wishlist.setProductId(productId);
        wishlist.setCreatedAt(new java.util.Date());
        
        wishlistRepository.save(wishlist);
        
        WishlistResponse response = new WishlistResponse();
        response.setMessage("Product added to wishlist successfully");
        return response;
    }
    
    public WishlistResponse removeFromWishlist(String email, Long productId) {
        User user = findByEmail(email);
        
        Wishlist wishlist = wishlistRepository.findByUserIdAndProductId(user.getId(), productId)
            .orElseThrow(() -> new RuntimeException("Product not found in wishlist"));
        
        wishlistRepository.delete(wishlist);
        
        WishlistResponse response = new WishlistResponse();
        response.setMessage("Product removed from wishlist successfully");
        return response;
    }
    
    public Page<NotificationResponse> getNotifications(String email, int page, int size, Boolean unreadOnly) {
        User user = findByEmail(email);
        Pageable pageable = PageRequest.of(page, size);
        
        Page<Notification> notifications;
        if (unreadOnly != null && unreadOnly) {
            notifications = notificationRepository.findByUserIdAndIsReadFalseOrderByCreatedAtDesc(user.getId(), pageable);
        } else {
            notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId(), pageable);
        }
        
        return notifications.map(notification -> {
            NotificationResponse response = new NotificationResponse();
            response.setId(notification.getId());
            response.setTitle(notification.getTitle());
            response.setMessage(notification.getMessage());
            response.setType(notification.getType());
            response.setIsRead(notification.getIsRead());
            response.setCreatedAt(notification.getCreatedAt());
            return response;
        });
    }
    
    public void markNotificationAsRead(String email, Long notificationId) {
        User user = findByEmail(email);
        
        Notification notification = notificationRepository.findByIdAndUserId(notificationId, user.getId())
            .orElseThrow(() -> new RuntimeException("Notification not found"));
        
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
    
    public void markAllNotificationsAsRead(String email) {
        User user = findByEmail(email);
        
        List<Notification> notifications = notificationRepository.findByUserIdAndIsReadFalse(user.getId());
        notifications.forEach(notification -> notification.setIsRead(true));
        notificationRepository.saveAll(notifications);
    }
    
    public void deleteAccount(String email, DeleteAccountRequest request) {
        User user = findByEmail(email);
        
        // Verify password or other authentication
        // This is a simplified implementation
        if (request.getConfirmation() == null || !request.getConfirmation().equals("DELETE")) {
            throw new RuntimeException("Invalid confirmation");
        }
        
        // Delete related data
        wishlistRepository.deleteByUserId(user.getId());
        notificationRepository.deleteByUserId(user.getId());
        userProfileRepository.deleteByUserId(user.getId());
        
        // Delete user
        userRepository.delete(user);
    }
}

