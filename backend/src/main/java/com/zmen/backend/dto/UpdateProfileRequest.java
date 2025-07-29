package com.zmen.backend.dto;

import com.zmen.backend.entity.UserProfile;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UpdateProfileRequest {
    
    @Size(max = 255, message = "Full name must not exceed 255 characters")
    private String fullName;
    
    @Email(message = "Invalid email format")
    private String email;
    
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    private String phoneNumber;
    
    private UserProfile.Gender gender;
    
    private LocalDate dateOfBirth;
    
    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;
    
    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String location;
    
    @Size(max = 200, message = "Website URL must not exceed 200 characters")
    private String website;
    
    private boolean newsletterSubscription;
    private boolean smsNotifications;
    private boolean emailNotifications;
    private boolean promotionalEmails;
    private String preferredLanguage;
    private String timezone;
    
    // Social media links
    @Size(max = 200, message = "Facebook URL must not exceed 200 characters")
    private String facebookUrl;
    
    @Size(max = 200, message = "Instagram URL must not exceed 200 characters")
    private String instagramUrl;
    
    @Size(max = 200, message = "Twitter URL must not exceed 200 characters")
    private String twitterUrl;
    
    // Privacy settings
    private boolean profilePublic;
    private boolean showEmail;
    private boolean showPhoneNumber;
    private boolean showBirthDate;
    
    // Constructors
    public UpdateProfileRequest() {}
    
    public UpdateProfileRequest(String fullName, String email, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public UserProfile.Gender getGender() { return gender; }
    public void setGender(UserProfile.Gender gender) { this.gender = gender; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    
    public boolean isNewsletterSubscription() { return newsletterSubscription; }
    public void setNewsletterSubscription(boolean newsletterSubscription) { this.newsletterSubscription = newsletterSubscription; }
    
    public boolean isSmsNotifications() { return smsNotifications; }
    public void setSmsNotifications(boolean smsNotifications) { this.smsNotifications = smsNotifications; }
    
    public boolean isEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    
    public boolean isPromotionalEmails() { return promotionalEmails; }
    public void setPromotionalEmails(boolean promotionalEmails) { this.promotionalEmails = promotionalEmails; }
    
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    
    public String getFacebookUrl() { return facebookUrl; }
    public void setFacebookUrl(String facebookUrl) { this.facebookUrl = facebookUrl; }
    
    public String getInstagramUrl() { return instagramUrl; }
    public void setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; }
    
    public String getTwitterUrl() { return twitterUrl; }
    public void setTwitterUrl(String twitterUrl) { this.twitterUrl = twitterUrl; }
    
    public boolean isProfilePublic() { return profilePublic; }
    public void setProfilePublic(boolean profilePublic) { this.profilePublic = profilePublic; }
    
    public boolean isShowEmail() { return showEmail; }
    public void setShowEmail(boolean showEmail) { this.showEmail = showEmail; }
    
    public boolean isShowPhoneNumber() { return showPhoneNumber; }
    public void setShowPhoneNumber(boolean showPhoneNumber) { this.showPhoneNumber = showPhoneNumber; }
    
    public boolean isShowBirthDate() { return showBirthDate; }
    public void setShowBirthDate(boolean showBirthDate) { this.showBirthDate = showBirthDate; }
}