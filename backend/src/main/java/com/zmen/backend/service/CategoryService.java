package com.zmen.backend.service;

import com.zmen.backend.dto.*;
import com.zmen.backend.entity.*;
import com.zmen.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private UserService userService;
    
    public List<CategoryResponse> getAllCategories(Boolean includeInactive, Boolean includeProductCount) {
        List<Category> categories;
        if (includeInactive != null && includeInactive) {
            categories = categoryRepository.findAll();
        } else {
            categories = categoryRepository.findByStatus(Category.CategoryStatus.dang_hoat_dong);
        }
        
        return categories.stream()
                .map(this::convertToCategoryResponse)
                .collect(java.util.stream.Collectors.toList());
    }
    
    public List<CategoryTreeResponse> getCategoryTree(Boolean includeInactive) {
        List<Category> rootCategories = categoryRepository.findByParentCategoryIsNullOrderByDisplayOrderAsc();
        return rootCategories.stream()
                .map(this::convertToCategoryTreeResponse)
                .collect(java.util.stream.Collectors.toList());
    }
    
    public Page<CategoryResponse> getCategoriesPaginated(String search, Boolean active, Pageable pageable) {
        Page<Category> categoryPage;
        if (search != null && !search.trim().isEmpty()) {
            categoryPage = categoryRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            categoryPage = categoryRepository.findAll(pageable);
        }
        
        List<CategoryResponse> categoryResponses = categoryPage.getContent().stream()
                .map(this::convertToCategoryResponse)
                .collect(java.util.stream.Collectors.toList());
        
        return new org.springframework.data.domain.PageImpl<>(categoryResponses, pageable, categoryPage.getTotalElements());
    }
    
    public CategoryDetailResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return convertToCategoryDetailResponse(category);
    }
    
    public CategoryDetailResponse getCategoryBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Category not found with slug: " + slug));
        return convertToCategoryDetailResponse(category);
    }
    
    public List<CategoryResponse> getChildCategories(Long id, Boolean includeInactive) {
        // Implementation for getting child categories
        throw new RuntimeException("CategoryService.getChildCategories not implemented yet");
    }
    
    public CategoryResponse createCategory(CreateCategoryRequest request, String createdBy) {
        // Implementation for creating category
        throw new RuntimeException("CategoryService.createCategory not implemented yet");
    }
    
    public CategoryResponse updateCategory(Long id, UpdateCategoryRequest request, String updatedBy) {
        // Implementation for updating category
        throw new RuntimeException("CategoryService.updateCategory not implemented yet");
    }
    
    public void deleteCategory(Long id, String deletedBy) {
        // Implementation for deleting category
        throw new RuntimeException("CategoryService.deleteCategory not implemented yet");
    }
    
    public CategoryResponse updateCategoryStatus(Long id, UpdateCategoryStatusRequest request, String updatedBy) {
        // Implementation for updating category status
        throw new RuntimeException("CategoryService.updateCategoryStatus not implemented yet");
    }
    
    public void reorderCategories(ReorderCategoriesRequest request, String updatedBy) {
        // Implementation for reordering categories
        throw new RuntimeException("CategoryService.reorderCategories not implemented yet");
    }
    
    public Page<ProductResponse> getProductsByCategory(Long id, Boolean includeSubcategories, Pageable pageable) {
        // Implementation for getting products by category
        throw new RuntimeException("CategoryService.getProductsByCategory not implemented yet");
    }
    
    public List<CategoryResponse> getFeaturedCategories(Integer limit) {
        // Implementation for getting featured categories
        throw new RuntimeException("CategoryService.getFeaturedCategories not implemented yet");
    }
    
    public List<CategoryResponse> getPopularCategories(Integer limit) {
        // Implementation for getting popular categories
        throw new RuntimeException("CategoryService.getPopularCategories not implemented yet");
    }
    
    public CategoryStatisticsResponse getCategoryStatistics(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        CategoryStatisticsResponse response = new CategoryStatisticsResponse();
        response.setCategoryId(id);
        response.setCategoryName(category.getCategoryName());
        response.setTotalProducts(0); // Would count from products
        // response.setTotalSales(0); // Field not available in DTO
        response.setTotalRevenue(java.math.BigDecimal.ZERO);
        return response;
    }
    
    private CategoryResponse convertToCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getCategoryName());
        response.setSlug(category.getSlug());
        response.setDescription(category.getDescription());
        // response.setImageUrl(category.getImage()); // Field not available in DTO
        // response.setIcon(category.getIcon()); // Field not available in DTO
        // response.setDisplayOrder(category.getDisplayOrder()); // Field not available in DTO
        response.setIsActive(category.getStatus() == Category.CategoryStatus.dang_hoat_dong);
        response.setProductCount(0); // Would count from products
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        return response;
    }
    
    private CategoryDetailResponse convertToCategoryDetailResponse(Category category) {
        CategoryDetailResponse response = new CategoryDetailResponse();
        response.setId(category.getId());
        response.setName(category.getCategoryName());
        response.setSlug(category.getSlug());
        response.setDescription(category.getDescription());
        // response.setImageUrl(category.getImage()); // Field not available in DTO
        // response.setIcon(category.getIcon()); // Field not available in DTO
        // response.setDisplayOrder(category.getDisplayOrder()); // Field not available in DTO
        response.setIsActive(category.getStatus() == Category.CategoryStatus.dang_hoat_dong);
        response.setProductCount(0); // Would count from products
        response.setCreatedAt(category.getCreatedAt());
        response.setUpdatedAt(category.getUpdatedAt());
        
        if (category.getParentCategory() != null) {
            response.setParentId(category.getParentCategory().getId());
            response.setParentName(category.getParentCategory().getCategoryName());
        }
        
        return response;
    }
    
    private CategoryTreeResponse convertToCategoryTreeResponse(Category category) {
        CategoryTreeResponse response = new CategoryTreeResponse();
        response.setId(category.getId());
        response.setName(category.getCategoryName());
        response.setSlug(category.getSlug());
        // response.setImageUrl(category.getImage()); // Field not available in DTO
        // response.setIcon(category.getIcon()); // Field not available in DTO
        // response.setDisplayOrder(category.getDisplayOrder()); // Field not available in DTO
        response.setIsActive(category.getStatus() == Category.CategoryStatus.dang_hoat_dong);
        
        if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
            List<CategoryTreeResponse> children = category.getSubCategories().stream()
                    .map(this::convertToCategoryTreeResponse)
                    .collect(java.util.stream.Collectors.toList());
            response.setChildren(children);
        }
        
        return response;
    }
}