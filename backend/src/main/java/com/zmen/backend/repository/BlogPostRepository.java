package com.zmen.backend.repository;

import com.zmen.backend.entity.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    
    Optional<BlogPost> findBySlug(String slug);
    
    List<BlogPost> findByStatusOrderByCreatedAtDesc(BlogPost.PostStatus status);
    
    Page<BlogPost> findByStatusOrderByCreatedAtDesc(BlogPost.PostStatus status, Pageable pageable);
    
    @Query("SELECT b FROM BlogPost b WHERE b.status = :status AND (b.title LIKE %:search% OR b.content LIKE %:search%)")
    Page<BlogPost> findByStatusAndSearchOrderByCreatedAtDesc(@Param("status") BlogPost.PostStatus status, @Param("search") String search, Pageable pageable);
    
    boolean existsBySlug(String slug);
    
    @Query("SELECT b FROM BlogPost b WHERE b.featured = true AND b.status = :status ORDER BY b.createdAt DESC")
    List<BlogPost> findFeaturedPosts(@Param("status") BlogPost.PostStatus status, Pageable pageable);
}