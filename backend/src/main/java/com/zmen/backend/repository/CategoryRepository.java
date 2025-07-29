package com.zmen.backend.repository;

import com.zmen.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    Optional<Category> findBySlug(String slug);
    
    List<Category> findByParentCategoryIsNullOrderByDisplayOrderAsc();
    
    List<Category> findByParentCategoryOrderByDisplayOrderAsc(Category parentCategory);
    
    List<Category> findByStatusOrderByDisplayOrderAsc(Category.CategoryStatus status);
    
    @Query("SELECT c FROM Category c WHERE c.parentCategory IS NULL AND c.status = :status ORDER BY c.displayOrder ASC")
    List<Category> findRootCategoriesByStatus(@Param("status") Category.CategoryStatus status);
    
    @Query("SELECT c FROM Category c WHERE c.parentCategory.id = :parentId AND c.status = :status ORDER BY c.displayOrder ASC")
    List<Category> findSubCategoriesByParentAndStatus(@Param("parentId") Long parentId, @Param("status") Category.CategoryStatus status);
    
    boolean existsBySlug(String slug);
    
    boolean existsByCategoryName(String categoryName);
    
    List<Category> findByStatus(Category.CategoryStatus status);
    
    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %:name% ORDER BY c.displayOrder ASC")
    org.springframework.data.domain.Page<Category> findByNameContainingIgnoreCase(@Param("name") String name, org.springframework.data.domain.Pageable pageable);
}