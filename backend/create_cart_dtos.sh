#!/bin/bash

# Create CartResponse.java
cat > src/main/java/com/zmen/backend/dto/CartResponse.java << 'JAVA'
package com.zmen.backend.dto;
import java.math.BigDecimal;
import java.util.List;
public class CartResponse {
    private Long id;
    private Integer totalItems;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private List<CartItemResponse> items;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public List<CartItemResponse> getItems() { return items; }
    public void setItems(List<CartItemResponse> items) { this.items = items; }
}
JAVA

# Create CartItemResponse.java
cat > src/main/java/com/zmen/backend/dto/CartItemResponse.java << 'JAVA'
package com.zmen.backend.dto;
import java.math.BigDecimal;
public class CartItemResponse {
    private Long id;
    private Long productId;
    private String productName;
    private String productImage;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String size;
    private String color;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductImage() { return productImage; }
    public void setProductImage(String productImage) { this.productImage = productImage; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
JAVA

# Create AddToCartRequest.java
cat > src/main/java/com/zmen/backend/dto/AddToCartRequest.java << 'JAVA'
package com.zmen.backend.dto;
public class AddToCartRequest {
    private Long productId;
    private Integer quantity;
    private String size;
    private String color;
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
JAVA

# Create UpdateCartItemRequest.java
cat > src/main/java/com/zmen/backend/dto/UpdateCartItemRequest.java << 'JAVA'
package com.zmen.backend.dto;
public class UpdateCartItemRequest {
    private Integer quantity;
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
JAVA

# Create CartSummaryResponse.java
cat > src/main/java/com/zmen/backend/dto/CartSummaryResponse.java << 'JAVA'
package com.zmen.backend.dto;
import java.math.BigDecimal;
public class CartSummaryResponse {
    private Integer totalItems;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    public Integer getTotalItems() { return totalItems; }
    public void setTotalItems(Integer totalItems) { this.totalItems = totalItems; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getDiscountAmount() { return discountAmount; }
    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
JAVA

# Create CartValidationResponse.java
cat > src/main/java/com/zmen/backend/dto/CartValidationResponse.java << 'JAVA'
package com.zmen.backend.dto;
public class CartValidationResponse {
    private Boolean valid;
    private String message;
    public Boolean getValid() { return valid; }
    public void setValid(Boolean valid) { this.valid = valid; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
JAVA

echo "Cart DTO files created successfully!"
