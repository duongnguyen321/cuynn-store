package com.zmen.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateBrandRequest {
    
    @NotBlank(message = "Brand name is required")
    @Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    private String logoUrl;
    
    @Size(max = 255, message = "Website URL cannot exceed 255 characters")
    private String website;
    
    @Size(max = 100, message = "Country cannot exceed 100 characters")
    private String country;
    
    private Integer foundedYear;
    
    private Boolean isActive = true;
    
    private Boolean isFeatured = false;
    
    // Constructors
    public CreateBrandRequest() {}
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public Integer getFoundedYear() { return foundedYear; }
    public void setFoundedYear(Integer foundedYear) { this.foundedYear = foundedYear; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }
}