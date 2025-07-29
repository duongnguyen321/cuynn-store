package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
@Tag(name = "Blog Management", description = "Blog and content management APIs")
@CrossOrigin(origins = "*")
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    
    @GetMapping("/posts")
    @Operation(summary = "Get blog posts", description = "Get published blog posts with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog posts retrieved successfully")
    })
    public ResponseEntity<?> getBlogPosts(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "12") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "publishedAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by category") @RequestParam(required = false) Long categoryId,
            @Parameter(description = "Search query") @RequestParam(required = false) String search,
            @Parameter(description = "Featured posts only") @RequestParam(defaultValue = "false") boolean featuredOnly) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            BlogSearchRequest searchRequest = new BlogSearchRequest();
            searchRequest.setCategoryId(categoryId);
            searchRequest.setSearch(search);
            searchRequest.setFeaturedOnly(featuredOnly);
            
            Page<BlogPostResponse> posts = blogService.getPublishedPosts(searchRequest, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog posts retrieved successfully", posts));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve blog posts: " + e.getMessage()));
        }
    }
    
    @GetMapping("/posts/{id}")
    @Operation(summary = "Get blog post by ID", description = "Get detailed blog post information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog post retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    public ResponseEntity<?> getBlogPostById(@Parameter(description = "Blog post ID") @PathVariable Long id) {
        try {
            BlogPostDetailResponse post = blogService.getBlogPostById(id);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog post retrieved successfully", post));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve blog post: " + e.getMessage()));
        }
    }
    
    @GetMapping("/posts/slug/{slug}")
    @Operation(summary = "Get blog post by slug", description = "Get blog post by URL slug")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog post retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    public ResponseEntity<?> getBlogPostBySlug(@Parameter(description = "Blog post slug") @PathVariable String slug) {
        try {
            BlogPostDetailResponse post = blogService.getBlogPostBySlug(slug);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog post retrieved successfully", post));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve blog post: " + e.getMessage()));
        }
    }
    
    @GetMapping("/posts/featured")
    @Operation(summary = "Get featured posts", description = "Get featured blog posts for homepage")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Featured posts retrieved successfully")
    })
    public ResponseEntity<?> getFeaturedPosts(
            @Parameter(description = "Maximum number of posts") @RequestParam(defaultValue = "6") int limit) {
        try {
            List<BlogPostResponse> posts = blogService.getFeaturedPosts(limit);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Featured posts retrieved successfully", posts));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve featured posts: " + e.getMessage()));
        }
    }
    
    @GetMapping("/posts/recent")
    @Operation(summary = "Get recent posts", description = "Get most recent blog posts")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recent posts retrieved successfully")
    })
    public ResponseEntity<?> getRecentPosts(
            @Parameter(description = "Maximum number of posts") @RequestParam(defaultValue = "10") int limit) {
        try {
            List<BlogPostResponse> posts = blogService.getRecentPosts(limit);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Recent posts retrieved successfully", posts));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve recent posts: " + e.getMessage()));
        }
    }
    
    @GetMapping("/categories")
    @Operation(summary = "Get blog categories", description = "Get all blog categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog categories retrieved successfully")
    })
    public ResponseEntity<?> getBlogCategories(
            @Parameter(description = "Include post count") @RequestParam(defaultValue = "true") boolean includePostCount) {
        try {
            List<BlogCategoryResponse> categories = blogService.getBlogCategories(includePostCount);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog categories retrieved successfully", categories));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve blog categories: " + e.getMessage()));
        }
    }
    
    @GetMapping("/posts/{postId}/comments")
    @Operation(summary = "Get post comments", description = "Get comments for a specific blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    public ResponseEntity<?> getPostComments(
            @Parameter(description = "Blog post ID") @PathVariable Long postId,
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
            Page<BlogCommentResponse> comments = blogService.getPostComments(postId, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Comments retrieved successfully", comments));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve comments: " + e.getMessage()));
        }
    }
    
    @PostMapping("/posts/{postId}/comments")
    @Operation(summary = "Add comment", description = "Add a comment to a blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Comment added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid comment data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> addComment(
            @Parameter(description = "Blog post ID") @PathVariable Long postId,
            @Valid @RequestBody AddBlogCommentRequest request,
            Principal principal) {
        try {
            BlogCommentResponse comment = blogService.addComment(postId, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Comment added successfully", comment));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to add comment: " + e.getMessage()));
        }
    }
    
    @PostMapping("/posts/{id}/view")
    @Operation(summary = "Track post view", description = "Track a view for a blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "View tracked successfully"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    public ResponseEntity<?> trackPostView(@Parameter(description = "Blog post ID") @PathVariable Long id) {
        try {
            blogService.trackPostView(id);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("View tracked successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to track view: " + e.getMessage()));
        }
    }
    
    @GetMapping("/admin/posts")
    @Operation(summary = "Get all posts (Admin)", description = "Get all blog posts for admin management")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posts retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getAllPosts(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Sort by field") @RequestParam(defaultValue = "createdAt") String sortBy,
            @Parameter(description = "Sort direction") @RequestParam(defaultValue = "desc") String sortDir,
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status,
            @Parameter(description = "Filter by category") @RequestParam(required = false) Long categoryId,
            @Parameter(description = "Search query") @RequestParam(required = false) String search) {
        try {
            Sort sort = Sort.by(sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            
            BlogSearchRequest searchRequest = new BlogSearchRequest();
            searchRequest.setStatus(status);
            searchRequest.setCategoryId(categoryId);
            searchRequest.setSearch(search);
            
            Page<BlogPostResponse> posts = blogService.getAllPosts(searchRequest, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Posts retrieved successfully", posts));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve posts: " + e.getMessage()));
        }
    }
    
    @PostMapping("/admin/posts")
    @Operation(summary = "Create blog post (Admin)", description = "Create a new blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Blog post created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid post data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createBlogPost(
            @Valid @RequestBody CreateBlogPostRequest request,
            Principal principal) {
        try {
            BlogPostResponse post = blogService.createBlogPost(request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog post created successfully", post));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to create blog post: " + e.getMessage()));
        }
    }
    
    @PutMapping("/admin/posts/{id}")
    @Operation(summary = "Update blog post (Admin)", description = "Update an existing blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog post updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid post data"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateBlogPost(
            @Parameter(description = "Blog post ID") @PathVariable Long id,
            @Valid @RequestBody UpdateBlogPostRequest request,
            Principal principal) {
        try {
            BlogPostResponse post = blogService.updateBlogPost(id, request, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog post updated successfully", post));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to update blog post: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/admin/posts/{id}")
    @Operation(summary = "Delete blog post (Admin)", description = "Soft delete a blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog post deleted successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteBlogPost(
            @Parameter(description = "Blog post ID") @PathVariable Long id,
            Principal principal) {
        try {
            blogService.deleteBlogPost(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog post deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete blog post: " + e.getMessage()));
        }
    }
    
    @PutMapping("/admin/posts/{id}/publish")
    @Operation(summary = "Publish blog post (Admin)", description = "Publish a draft blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog post published successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Blog post not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> publishBlogPost(
            @Parameter(description = "Blog post ID") @PathVariable Long id,
            Principal principal) {
        try {
            BlogPostResponse post = blogService.publishBlogPost(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Blog post published successfully", post));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to publish blog post: " + e.getMessage()));
        }
    }
    
    @GetMapping("/admin/comments")
    @Operation(summary = "Get all comments (Admin)", description = "Get all blog comments for moderation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> getAllComments(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
            Page<BlogCommentResponse> comments = blogService.getAllComments(status, pageable);
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Comments retrieved successfully", comments));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to retrieve comments: " + e.getMessage()));
        }
    }
    
    @PutMapping("/admin/comments/{id}/approve")
    @Operation(summary = "Approve comment (Admin)", description = "Approve a pending comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comment approved successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> approveComment(
            @Parameter(description = "Comment ID") @PathVariable Long id,
            Principal principal) {
        try {
            BlogCommentResponse comment = blogService.approveComment(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Comment approved successfully", comment));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to approve comment: " + e.getMessage()));
        }
    }
    
    @DeleteMapping("/admin/comments/{id}")
    @Operation(summary = "Delete comment (Admin)", description = "Delete a comment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comment deleted successfully"),
        @ApiResponse(responseCode = "401", description = "User not authenticated"),
        @ApiResponse(responseCode = "403", description = "Access denied - Admin role required"),
        @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteComment(
            @Parameter(description = "Comment ID") @PathVariable Long id,
            Principal principal) {
        try {
            blogService.deleteComment(id, principal.getName());
            return ResponseEntity.ok(com.zmen.backend.dto.ApiResponse.success("Comment deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(com.zmen.backend.dto.ApiResponse.error("Failed to delete comment: " + e.getMessage()));
        }
    }
}