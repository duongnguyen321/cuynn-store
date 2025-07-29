package com.zmen.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UploadAvatarRequest {
    
    @NotNull(message = "File data is required")
    private String fileData; // Base64 encoded file data
    
    @NotNull(message = "File name is required")
    @Size(max = 255, message = "File name must not exceed 255 characters")
    private String fileName;
    
    @NotNull(message = "File type is required")
    @Pattern(regexp = "^image/(jpeg|jpg|png|gif|webp)$", message = "Only image files are allowed (JPEG, JPG, PNG, GIF, WebP)")
    private String fileType;
    
    private Long fileSize; // in bytes
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
    
    // Image processing options
    private Integer maxWidth = 400;
    private Integer maxHeight = 400;
    private Integer quality = 85; // JPEG quality (1-100)
    private boolean cropToSquare = true;
    private boolean generateThumbnail = true;
    
    // Constructors
    public UploadAvatarRequest() {}
    
    public UploadAvatarRequest(String fileData, String fileName, String fileType) {
        this.fileData = fileData;
        this.fileName = fileName;
        this.fileType = fileType;
    }
    
    // Getters and Setters
    public String getFileData() { return fileData; }
    public void setFileData(String fileData) { this.fileData = fileData; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getMaxWidth() { return maxWidth; }
    public void setMaxWidth(Integer maxWidth) { this.maxWidth = maxWidth; }
    
    public Integer getMaxHeight() { return maxHeight; }
    public void setMaxHeight(Integer maxHeight) { this.maxHeight = maxHeight; }
    
    public Integer getQuality() { return quality; }
    public void setQuality(Integer quality) { this.quality = quality; }
    
    public boolean isCropToSquare() { return cropToSquare; }
    public void setCropToSquare(boolean cropToSquare) { this.cropToSquare = cropToSquare; }
    
    public boolean isGenerateThumbnail() { return generateThumbnail; }
    public void setGenerateThumbnail(boolean generateThumbnail) { this.generateThumbnail = generateThumbnail; }
    
    // Utility methods
    public boolean isValidImageType() {
        if (fileType == null) return false;
        return fileType.matches("^image/(jpeg|jpg|png|gif|webp)$");
    }
    
    public boolean isValidFileSize() {
        if (fileSize == null) return true; // Size validation can be optional
        // Maximum 5MB
        return fileSize <= 5 * 1024 * 1024;
    }
    
    public String getFileExtension() {
        if (fileName == null) return null;
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1).toLowerCase();
        }
        return null;
    }
}