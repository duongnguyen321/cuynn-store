package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.CartService;
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
@RequestMapping("/api/cart")
@Tag(name = "Shopping Cart", description = "Shopping cart management APIs")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @GetMapping
    @Operation(summary = "Get user's cart", description = "Retrieve the current user's shopping cart with all items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCart(Principal principal) {
        try {
            CartResponse cart = cartService.getUserCart(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Cart retrieved successfully", cart));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve cart: " + e.getMessage()));
        }
    }
    
    @PostMapping("/items")
    @Operation(summary = "Add item to cart", description = "Add a product variant to the shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item added to cart successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request or insufficient stock"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Product variant not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addToCart(
            @Valid @RequestBody AddToCartRequest request,
            Principal principal) {
        try {
            CartItemResponse cartItem = cartService.addToCart(principal.getName(), request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Item added to cart successfully", cartItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to add item to cart: " + e.getMessage()));
        }
    }
    
    @PutMapping("/items/{itemId}")
    @Operation(summary = "Update cart item quantity", description = "Update the quantity of a specific item in the cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart item updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid quantity or insufficient stock"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Not authorized to update this cart item"),
        @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateCartItem(
            @Parameter(description = "Cart item ID") @PathVariable Long itemId,
            @Valid @RequestBody UpdateCartItemRequest request,
            Principal principal) {
        try {
            CartItemResponse cartItem = cartService.updateCartItem(principal.getName(), itemId, request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Cart item updated successfully", cartItem));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update cart item: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/items/{itemId}")
    @Operation(summary = "Remove item from cart", description = "Remove a specific item from the shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Item removed from cart successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Not authorized to remove this cart item"),
        @ApiResponse(responseCode = "404", description = "Cart item not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeFromCart(
            @Parameter(description = "Cart item ID") @PathVariable Long itemId,
            Principal principal) {
        try {
            cartService.removeFromCart(principal.getName(), itemId);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Item removed from cart successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to remove item from cart: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/clear")
    @Operation(summary = "Clear cart", description = "Remove all items from the shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart cleared successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> clearCart(Principal principal) {
        try {
            cartService.clearCart(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Cart cleared successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to clear cart: " + e.getMessage()));
        }
    }
    
    @GetMapping("/summary")
    @Operation(summary = "Get cart summary", description = "Get cart summary with totals and item count")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart summary retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCartSummary(Principal principal) {
        try {
            CartSummaryResponse summary = cartService.getCartSummary(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Cart summary retrieved successfully", summary));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve cart summary: " + e.getMessage()));
        }
    }
    
    @PostMapping("/apply-voucher")
    @Operation(summary = "Apply voucher to cart", description = "Apply a discount voucher to the shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher applied successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid voucher or voucher not applicable"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Voucher not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> applyVoucher(
            @Valid @RequestBody ApplyVoucherRequest request,
            Principal principal) {
        try {
            CartResponse cart = cartService.applyVoucher(principal.getName(), request.getVoucherCode());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher applied successfully", cart));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to apply voucher: " + e.getMessage()));
        }
    }
    
    @PostMapping("/remove-voucher")
    @Operation(summary = "Remove voucher from cart", description = "Remove the applied voucher from the shopping cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voucher removed successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Cart not found or no voucher applied")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeVoucher(Principal principal) {
        try {
            CartResponse cart = cartService.removeVoucher(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Voucher removed successfully", cart));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to remove voucher: " + e.getMessage()));
        }
    }
    
    @PostMapping("/validate")
    @Operation(summary = "Validate cart", description = "Validate cart items for availability and pricing before checkout")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart validation completed"),
        @ApiResponse(responseCode = "400", description = "Cart validation failed"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Cart not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> validateCart(Principal principal) {
        try {
            CartValidationResponse validation = cartService.validateCart(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Cart validation completed", validation));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Cart validation failed: " + e.getMessage()));
        }
    }
    
    @GetMapping("/count")
    @Operation(summary = "Get cart item count", description = "Get the total number of items in the cart")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cart item count retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCartItemCount(Principal principal) {
        try {
            Integer count = cartService.getCartItemCount(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Cart item count retrieved successfully", count));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve cart item count: " + e.getMessage()));
        }
    }
}

