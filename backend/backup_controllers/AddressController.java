package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.AddressService;
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
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@Tag(name = "Address Management", description = "User address management APIs")
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
public class AddressController {
    
    @Autowired
    private AddressService addressService;
    
    @GetMapping
    @Operation(summary = "Get user addresses", description = "Get all addresses of the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserAddresses(Principal principal) {
        try {
            List<AddressResponse> addresses = addressService.getUserAddresses(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Addresses retrieved successfully", addresses));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve addresses: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID", description = "Get a specific address by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your address"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAddressById(
            @Parameter(description = "Address ID") @PathVariable Long id,
            Principal principal) {
        try {
            AddressResponse address = addressService.getAddressById(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Address retrieved successfully", address));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve address: " + e.getMessage()));
        }
    }
    
    @GetMapping("/default")
    @Operation(summary = "Get default address", description = "Get the user's default shipping address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Default address retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "No default address found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getDefaultAddress(Principal principal) {
        try {
            AddressResponse address = addressService.getDefaultAddress(principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Default address retrieved successfully", address));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve default address: " + e.getMessage()));
        }
    }
    
    @PostMapping
    @Operation(summary = "Create address", description = "Create a new address for the current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Address created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid address data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createAddress(
            @Valid @RequestBody CreateAddressRequest request,
            Principal principal) {
        try {
            AddressResponse address = addressService.createAddress(principal.getName(), request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Address created successfully", address));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create address: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update address", description = "Update an existing address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Address updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid address data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your address"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateAddress(
            @Parameter(description = "Address ID") @PathVariable Long id,
            @Valid @RequestBody UpdateAddressRequest request,
            Principal principal) {
        try {
            AddressResponse address = addressService.updateAddress(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Address updated successfully", address));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update address: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete address", description = "Delete an address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Address deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete default address or address in use"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your address"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteAddress(
            @Parameter(description = "Address ID") @PathVariable Long id,
            Principal principal) {
        try {
            addressService.deleteAddress(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Address deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete address: " + e.getMessage()));
        }
    }
    
    @PutMapping("/{id}/set-default")
    @Operation(summary = "Set default address", description = "Set an address as the default shipping address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Default address updated successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your address"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> setDefaultAddress(
            @Parameter(description = "Address ID") @PathVariable Long id,
            Principal principal) {
        try {
            AddressResponse address = addressService.setDefaultAddress(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Default address updated successfully", address));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to set default address: " + e.getMessage()));
        }
    }
    
    @GetMapping("/provinces")
    @Operation(summary = "Get provinces", description = "Get list of available provinces/states")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Provinces retrieved successfully")
    })
    public ResponseEntity<?> getProvinces() {
        try {
            List<ProvinceResponse> provinces = addressService.getProvinces();
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Provinces retrieved successfully", provinces));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve provinces: " + e.getMessage()));
        }
    }
    
    @GetMapping("/provinces/{provinceId}/districts")
    @Operation(summary = "Get districts", description = "Get districts by province ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Districts retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Province not found")
    })
    public ResponseEntity<?> getDistrictsByProvince(
            @Parameter(description = "Province ID") @PathVariable Long provinceId) {
        try {
            List<DistrictResponse> districts = addressService.getDistrictsByProvince(provinceId);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Districts retrieved successfully", districts));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve districts: " + e.getMessage()));
        }
    }
    
    @GetMapping("/districts/{districtId}/wards")
    @Operation(summary = "Get wards", description = "Get wards by district ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Wards retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "District not found")
    })
    public ResponseEntity<?> getWardsByDistrict(
            @Parameter(description = "District ID") @PathVariable Long districtId) {
        try {
            List<WardResponse> wards = addressService.getWardsByDistrict(districtId);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Wards retrieved successfully", wards));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve wards: " + e.getMessage()));
        }
    }
    
    @PostMapping("/validate")
    @Operation(summary = "Validate address", description = "Validate an address format and deliverability")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Address validation completed"),
        @ApiResponse(responseCode = "400", description = "Invalid address data")
    })
    public ResponseEntity<?> validateAddress(@Valid @RequestBody ValidateAddressRequest request) {
        try {
            AddressValidationResponse validation = addressService.validateAddress(request);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Address validation completed", validation));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to validate address: " + e.getMessage()));
        }
    }
    
    @GetMapping("/{id}/shipping-cost")
    @Operation(summary = "Calculate shipping cost", description = "Calculate shipping cost to a specific address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Shipping cost calculated successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - not your address"),
        @ApiResponse(responseCode = "404", description = "Address not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> calculateShippingCost(
            @Parameter(description = "Address ID") @PathVariable Long id,
            @Parameter(description = "Total weight in grams") @RequestParam(required = false, defaultValue = "500") int weight,
            @Parameter(description = "Total value for insurance") @RequestParam(required = false, defaultValue = "0") double value,
            Principal principal) {
        try {
            ShippingCostResponse shippingCost = addressService.calculateShippingCost(id, weight, value, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Shipping cost calculated successfully", shippingCost));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to calculate shipping cost: " + e.getMessage()));
        }
    }
}