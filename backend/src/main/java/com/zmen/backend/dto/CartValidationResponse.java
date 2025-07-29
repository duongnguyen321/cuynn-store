package com.zmen.backend.dto;
public class CartValidationResponse {
    private Boolean valid;
    private String message;
    public Boolean getValid() { return valid; }
    public void setValid(Boolean valid) { this.valid = valid; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
