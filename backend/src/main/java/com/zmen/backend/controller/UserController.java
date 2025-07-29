package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "User profile and account management APIs")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/profile")
    @Operation(summary = "Get user profile", description = "Get the current user's profile information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "User profile not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserProfile(Principal principal) {
        try {
            UserProfileResponse profile = userService.getUserProfile(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Profile retrieved successfully", profile));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve profile: " + e.getMessage()));
        }
    }
    
    @PutMapping("/profile")
    @Operation(summary = "Update user profile", description = "Update the current user's profile information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid profile data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUserProfile(
            @Valid @RequestBody UpdateUserProfileRequest request,
            Principal principal) {
        try {
            UserProfileResponse profile = userService.updateUserProfile(principal.getName(), request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Profile updated successfully", profile));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update profile: " + e.getMessage()));
        }
    }
    
    @PostMapping("/profile/avatar")
    @Operation(summary = "Upload user avatar", description = "Upload and update user's profile picture")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avatar uploaded successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid file or upload failed"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> uploadAvatar(
            @Valid @RequestBody UploadAvatarRequest request,
            Principal principal) {
        try {
            UserProfileResponse profile = userService.uploadAvatar(principal.getName(), request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Avatar uploaded successfully", profile));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to upload avatar: " + e.getMessage()));
        }
    }
    
    @PostMapping("/change-password")
    @Operation(summary = "Change password", description = "Change the current user's password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Password changed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid current password or new password"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Principal principal) {
        try {
            userService.changePassword(principal.getName(), request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Password changed successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to change password: " + e.getMessage()));
        }
    }
    
    @GetMapping("/loyalty-points")
    @Operation(summary = "Get loyalty points", description = "Get user's loyalty points balance and history")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Loyalty points retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getLoyaltyPoints(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            Principal principal) {
        try {
            LoyaltyPointsResponse loyaltyPoints = userService.getLoyaltyPoints(principal.getName(), page, size);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Loyalty points retrieved successfully", loyaltyPoints));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve loyalty points: " + e.getMessage()));
        }
    }
    
    @GetMapping("/membership")
    @Operation(summary = "Get membership info", description = "Get user's membership tier and benefits")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Membership info retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getMembershipInfo(Principal principal) {
        try {
            MembershipInfoResponse membership = userService.getMembershipInfo(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Membership info retrieved successfully", membership));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve membership info: " + e.getMessage()));
        }
    }
    
    @GetMapping("/wishlist")
    @Operation(summary = "Get user wishlist", description = "Get user's wishlist with paginated products")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Wishlist retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getWishlist(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            Principal principal) {
        try {
            WishlistResponse wishlist = userService.getWishlist(principal.getName(), page, size);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Wishlist retrieved successfully", wishlist));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve wishlist: " + e.getMessage()));
        }
    }
    
    @PostMapping("/wishlist/{productId}")
    @Operation(summary = "Add to wishlist", description = "Add a product to user's wishlist")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product added to wishlist successfully"),
        @ApiResponse(responseCode = "400", description = "Product already in wishlist or invalid product"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addToWishlist(
            @Parameter(description = "Product ID") @PathVariable Long productId,
            Principal principal) {
        try {
            userService.addToWishlist(principal.getName(), productId);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Product added to wishlist successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to add to wishlist: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/wishlist/{productId}")
    @Operation(summary = "Remove from wishlist", description = "Remove a product from user's wishlist")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product removed from wishlist successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Product not found in wishlist")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeFromWishlist(
            @Parameter(description = "Product ID") @PathVariable Long productId,
            Principal principal) {
        try {
            userService.removeFromWishlist(principal.getName(), productId);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Product removed from wishlist successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to remove from wishlist: " + e.getMessage()));
        }
    }
    
    @GetMapping("/notifications")
    @Operation(summary = "Get user notifications", description = "Get user's notifications with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notifications retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getNotifications(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Filter by read status") @RequestParam(required = false) Boolean isRead,
            Principal principal) {
        try {
            NotificationResponse notifications = userService.getNotifications(principal.getName(), page, size, isRead);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Notifications retrieved successfully", notifications));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve notifications: " + e.getMessage()));
        }
    }
    
    @PutMapping("/notifications/{notificationId}/read")
    @Operation(summary = "Mark notification as read", description = "Mark a specific notification as read")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notification marked as read successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Not authorized to access this notification"),
        @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> markNotificationAsRead(
            @Parameter(description = "Notification ID") @PathVariable Long notificationId,
            Principal principal) {
        try {
            userService.markNotificationAsRead(principal.getName(), notificationId);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Notification marked as read successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to mark notification as read: " + e.getMessage()));
        }
    }
    
    @PutMapping("/notifications/read-all")
    @Operation(summary = "Mark all notifications as read", description = "Mark all user's notifications as read")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "All notifications marked as read successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> markAllNotificationsAsRead(Principal principal) {
        try {
            userService.markAllNotificationsAsRead(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("All notifications marked as read successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to mark notifications as read: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/account")
    @Operation(summary = "Delete user account", description = "Soft delete the user's account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete account with pending orders"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAccount(
            @Valid @RequestBody DeleteAccountRequest request,
            Principal principal) {
        try {
            userService.deleteAccount(principal.getName(), request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Account deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete account: " + e.getMessage()));
        }
    }
}