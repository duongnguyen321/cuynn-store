package com.zmen.backend.config;

import com.zmen.backend.entity.Product;
import com.zmen.backend.entity.User;
import com.zmen.backend.repository.ProductRepository;
import com.zmen.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        initializeProducts();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            // Admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@zmen.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Administrator");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);

            // Employee user
            User employee = new User();
            employee.setUsername("employee");
            employee.setEmail("employee@zmen.com");
            employee.setPassword(passwordEncoder.encode("employee123"));
            employee.setFullName("Employee User");
            employee.setRole(User.Role.EMPLOYEE);
            userRepository.save(employee);

            // Customer user
            User customer = new User();
            customer.setUsername("customer");
            customer.setEmail("customer@zmen.com");
            customer.setPassword(passwordEncoder.encode("customer123"));
            customer.setFullName("Customer User");
            customer.setRole(User.Role.CUSTOMER);
            userRepository.save(customer);

            System.out.println("✅ Initialized 3 users");
        }
    }

    private void initializeProducts() {
        if (productRepository.count() == 0) {
            // Áo sơ mi
            Product shirt1 = new Product();
            shirt1.setName("Áo Sơ Mi Nam Trắng Classic");
            shirt1.setDescription("Áo sơ mi nam màu trắng cổ điển, chất liệu cotton cao cấp, phù hợp cho công sở và dự tiệc");
            shirt1.setPrice(new BigDecimal("299000"));
            shirt1.setOriginalPrice(new BigDecimal("399000"));
            shirt1.setDiscountPercentage(new BigDecimal("25"));
            shirt1.setCategory("Áo sơ mi");
            shirt1.setBrand("ZMEN");
            shirt1.setImageUrl("https://images.unsplash.com/photo-1596755094514-f87e34085b2c?w=500");
            shirt1.setRating(new BigDecimal("4.5"));
            shirt1.setReviewsCount(128);
            shirt1.setStockQuantity(50);
            shirt1.setSoldQuantity(85);
            shirt1.setFeatured(true);
            shirt1.setBestSeller(true);
            productRepository.save(shirt1);

            // Áo polo
            Product polo1 = new Product();
            polo1.setName("Áo Polo Nam Xanh Navy");
            polo1.setDescription("Áo polo nam màu xanh navy, thiết kế trẻ trung, chất liệu pique cotton thoáng mát");
            polo1.setPrice(new BigDecimal("249000"));
            polo1.setOriginalPrice(new BigDecimal("299000"));
            polo1.setDiscountPercentage(new BigDecimal("17"));
            polo1.setCategory("Áo polo");
            polo1.setBrand("ZMEN");
            polo1.setImageUrl("https://images.unsplash.com/photo-1586790170083-2f9ceadc732d?w=500");
            polo1.setRating(new BigDecimal("4.3"));
            polo1.setReviewsCount(95);
            polo1.setStockQuantity(75);
            polo1.setSoldQuantity(62);
            polo1.setFeatured(true);
            productRepository.save(polo1);

            // Quần jeans
            Product jeans1 = new Product();
            jeans1.setName("Quần Jeans Nam Slim Fit");
            jeans1.setDescription("Quần jeans nam dáng slim fit, màu xanh đậm, chất liệu denim cao cấp co giãn nhẹ");
            jeans1.setPrice(new BigDecimal("599000"));
            jeans1.setOriginalPrice(new BigDecimal("799000"));
            jeans1.setDiscountPercentage(new BigDecimal("25"));
            jeans1.setCategory("Quần jeans");
            jeans1.setBrand("ZMEN");
            jeans1.setImageUrl("https://images.unsplash.com/photo-1542272454315-7ad9f0b2c0d8?w=500");
            jeans1.setRating(new BigDecimal("4.7"));
            jeans1.setReviewsCount(156);
            jeans1.setStockQuantity(40);
            jeans1.setSoldQuantity(98);
            jeans1.setFeatured(true);
            jeans1.setBestSeller(true);
            productRepository.save(jeans1);

            // Áo thun
            Product tshirt1 = new Product();
            tshirt1.setName("Áo Thun Nam Basic Đen");
            tshirt1.setDescription("Áo thun nam cổ tròn màu đen, chất liệu cotton 100%, form regular fit thoải mái");
            tshirt1.setPrice(new BigDecimal("149000"));
            tshirt1.setOriginalPrice(new BigDecimal("199000"));
            tshirt1.setDiscountPercentage(new BigDecimal("25"));
            tshirt1.setCategory("Áo thun");
            tshirt1.setBrand("ZMEN");
            tshirt1.setImageUrl("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500");
            tshirt1.setRating(new BigDecimal("4.2"));
            tshirt1.setReviewsCount(203);
            tshirt1.setStockQuantity(100);
            tshirt1.setSoldQuantity(145);
            tshirt1.setIsNew(true);
            productRepository.save(tshirt1);

            // Áo khoác
            Product jacket1 = new Product();
            jacket1.setName("Áo Khoác Bomber Nam");
            jacket1.setDescription("Áo khoác bomber nam màu xanh rêu, chất liệu polyester chống gió, thiết kế hiện đại");
            jacket1.setPrice(new BigDecimal("799000"));
            jacket1.setOriginalPrice(new BigDecimal("999000"));
            jacket1.setDiscountPercentage(new BigDecimal("20"));
            jacket1.setCategory("Áo khoác");
            jacket1.setBrand("ZMEN");
            jacket1.setImageUrl("https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500");
            jacket1.setRating(new BigDecimal("4.6"));
            jacket1.setReviewsCount(87);
            jacket1.setStockQuantity(30);
            jacket1.setSoldQuantity(43);
            jacket1.setFeatured(true);
            jacket1.setIsNew(true);
            productRepository.save(jacket1);

            // Quần short
            Product shorts1 = new Product();
            shorts1.setName("Quần Short Nam Kaki");
            shorts1.setDescription("Quần short nam chất liệu kaki, màu be, dáng regular fit, phù hợp mùa hè");
            shorts1.setPrice(new BigDecimal("199000"));
            shorts1.setOriginalPrice(new BigDecimal("249000"));
            shorts1.setDiscountPercentage(new BigDecimal("20"));
            shorts1.setCategory("Quần short");
            shorts1.setBrand("ZMEN");
            shorts1.setImageUrl("https://images.unsplash.com/photo-1506629905607-45c49c3b4e8d?w=500");
            shorts1.setRating(new BigDecimal("4.1"));
            shorts1.setReviewsCount(74);
            shorts1.setStockQuantity(60);
            shorts1.setSoldQuantity(56);
            shorts1.setIsNew(true);
            productRepository.save(shorts1);

            System.out.println("✅ Initialized 6 products");
        }
    }
}

