package com.zmen.backend.service;

import com.zmen.backend.dto.*;
import com.zmen.backend.entity.*;
import com.zmen.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrandService {
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<BrandResponse> getAllBrands(Boolean includeInactive, Boolean includeProductCount) {
        List<Brand> brands;
        if (includeInactive != null && includeInactive) {
            brands = brandRepository.findAll();
        } else {
            brands = brandRepository.findActiveBrands(Brand.BrandStatus.dang_hoat_dong);
        }
        
        return brands.stream()
                .map(this::convertToBrandResponse)
                .collect(Collectors.toList());
    }
    
    public Page<BrandResponse> getBrandsPaginated(String search, Boolean active, Pageable pageable) {
        Page<Brand> brandPage;
        if (search != null && !search.trim().isEmpty()) {
            // For now, get all and filter (would be better with custom query)
            List<Brand> allBrands = brandRepository.findAll();
            List<Brand> filteredBrands = allBrands.stream()
                    .filter(brand -> brand.getBrandName().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
            brandPage = new PageImpl<>(filteredBrands, pageable, filteredBrands.size());
        } else {
            brandPage = brandRepository.findAll(pageable);
        }
        
        List<BrandResponse> brandResponses = brandPage.getContent().stream()
                .map(this::convertToBrandResponse)
                .collect(Collectors.toList());
        
        return new PageImpl<>(brandResponses, pageable, brandPage.getTotalElements());
    }
    
    public BrandDetailResponse getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        return convertToBrandDetailResponse(brand);
    }
    
    public BrandDetailResponse getBrandBySlug(String slug) {
        Brand brand = brandRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Brand not found with slug: " + slug));
        return convertToBrandDetailResponse(brand);
    }
    
    public BrandResponse createBrand(CreateBrandRequest request, String createdBy) {
        if (brandRepository.existsByBrandName(request.getName())) {
            throw new RuntimeException("Brand name already exists");
        }
        
        Brand brand = new Brand();
        brand.setBrandName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setWebsite(request.getWebsite());
        brand.setCountry(request.getCountry());
        brand.setLogo(request.getLogoUrl());
        
        Brand savedBrand = brandRepository.save(brand);
        return convertToBrandResponse(savedBrand);
    }
    
    public BrandResponse updateBrand(Long id, UpdateBrandRequest request, String updatedBy) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        
        brand.setBrandName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setWebsite(request.getWebsite());
        brand.setCountry(request.getCountry());
        brand.setLogo(request.getLogoUrl());
        
        Brand updatedBrand = brandRepository.save(brand);
        return convertToBrandResponse(updatedBrand);
    }
    
    public void deleteBrand(Long id, String deletedBy) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        brand.setStatus(Brand.BrandStatus.tam_ngung);
        brandRepository.save(brand);
    }
    
    public BrandResponse updateBrandStatus(Long id, UpdateBrandStatusRequest request, String updatedBy) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        
        brand.setStatus(request.getIsActive() ? Brand.BrandStatus.dang_hoat_dong : Brand.BrandStatus.tam_ngung);
        Brand updatedBrand = brandRepository.save(brand);
        return convertToBrandResponse(updatedBrand);
    }
    
    public Page<ProductResponse> getProductsByBrand(Long id, Pageable pageable) {
        // This would need ProductService integration
        return new PageImpl<>(List.of(), pageable, 0);
    }
    
    public List<BrandResponse> getFeaturedBrands(Integer limit) {
        List<Brand> brands = brandRepository.findActiveBrands(Brand.BrandStatus.dang_hoat_dong)
                .stream()
                .limit(limit != null ? limit : 10)
                .collect(Collectors.toList());
        
        return brands.stream()
                .map(this::convertToBrandResponse)
                .collect(Collectors.toList());
    }
    
    public List<BrandResponse> getPopularBrands(Integer limit) {
        // Same as featured for now - would need product sales data
        return getFeaturedBrands(limit);
    }
    
    public Page<BrandResponse> searchBrands(String query, Pageable pageable) {
        return getBrandsPaginated(query, true, pageable);
    }
    
    public BrandStatisticsResponse getBrandStatistics(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));
        
        BrandStatisticsResponse response = new BrandStatisticsResponse();
        response.setBrandId(id);
        response.setBrandName(brand.getBrandName());
        response.setTotalProducts(0); // Would count from products
        response.setActiveProducts(0);
        response.setInactiveProducts(0);
        response.setTotalRevenue(java.math.BigDecimal.ZERO);
        response.setAverageProductPrice(java.math.BigDecimal.ZERO);
        response.setTotalOrders(0);
        response.setTotalReviews(0);
        response.setAverageRating(0.0);
        response.setViewCount(0);
        response.setFavoriteCount(0);
        return response;
    }
    
    private BrandResponse convertToBrandResponse(Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getBrandName());
        response.setSlug(brand.getSlug());
        response.setDescription(brand.getDescription());
        response.setLogoUrl(brand.getLogo());
        response.setWebsite(brand.getWebsite());
        response.setIsActive(brand.getStatus() == Brand.BrandStatus.dang_hoat_dong);
        response.setIsFeatured(false); // Default
        response.setProductCount(0); // Would count from products
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());
        return response;
    }
    
    private BrandDetailResponse convertToBrandDetailResponse(Brand brand) {
        BrandDetailResponse response = new BrandDetailResponse();
        response.setId(brand.getId());
        response.setName(brand.getBrandName());
        response.setSlug(brand.getSlug());
        response.setDescription(brand.getDescription());
        response.setLogoUrl(brand.getLogo());
        response.setWebsite(brand.getWebsite());
        response.setIsActive(brand.getStatus() == Brand.BrandStatus.dang_hoat_dong);
        response.setIsFeatured(false); // Default
        response.setProductCount(0); // Would count from products
        response.setCreatedAt(brand.getCreatedAt());
        response.setUpdatedAt(brand.getUpdatedAt());
        return response;
    }
}