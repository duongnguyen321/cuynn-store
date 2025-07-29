package com.zmen.backend.config;

import com.zmen.backend.entity.Product;
import com.zmen.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            initializeProducts();
        }
    }

    private void initializeProducts() {
        // Áo thun
        Product shirt1 = new Product();
        shirt1.setName("Áo Thun Nam Basic Trắng");
        shirt1.setDescription("Áo thun nam màu trắng basic, chất liệu cotton 100%, form regular fit thoải mái");
        shirt1.setPrice(new BigDecimal("199000"));
        shirt1.setImageUrl("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=500");
        shirt1.setStock(100);
        shirt1.setStatus(Product.ProductStatus.DANG_BAN);
        productRepository.save(shirt1);

        // Áo polo
        Product polo1 = new Product();
        polo1.setName("Áo Polo Nam Xanh Navy");
        polo1.setDescription("Áo polo nam màu xanh navy, thiết kế trẻ trung, chất liệu pique cotton thoáng mát");
        polo1.setPrice(new BigDecimal("249000"));
        polo1.setImageUrl("https://images.unsplash.com/photo-1586790170083-2f9ceadc732d?w=500");
        polo1.setStock(75);
        polo1.setStatus(Product.ProductStatus.DANG_BAN);
        productRepository.save(polo1);

        // Quần jeans
        Product jeans1 = new Product();
        jeans1.setName("Quần Jeans Nam Slim Fit");
        jeans1.setDescription("Quần jeans nam form slim fit, màu xanh đậm, chất liệu denim cao cấp");
        jeans1.setPrice(new BigDecimal("399000"));
        jeans1.setImageUrl("https://images.unsplash.com/photo-1542272604-787c3835535d?w=500");
        jeans1.setStock(50);
        jeans1.setStatus(Product.ProductStatus.DANG_BAN);
        productRepository.save(jeans1);

        // Áo thun
        Product tshirt1 = new Product();
        tshirt1.setName("Áo Thun Nam Đen Basic");
        tshirt1.setDescription("Áo thun nam màu đen basic, chất liệu cotton mềm mại, thiết kế đơn giản");
        tshirt1.setPrice(new BigDecimal("179000"));
        tshirt1.setImageUrl("https://images.unsplash.com/photo-1503341504253-dff4815485f1?w=500");
        tshirt1.setStock(120);
        tshirt1.setStatus(Product.ProductStatus.DANG_BAN);
        productRepository.save(tshirt1);

        // Áo khoác
        Product jacket1 = new Product();
        jacket1.setName("Áo Khoác Nam Bomber");
        jacket1.setDescription("Áo khoác bomber nam, thiết kế hiện đại, chất liệu polyester chống gió");
        jacket1.setPrice(new BigDecimal("599000"));
        jacket1.setImageUrl("https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500");
        jacket1.setStock(30);
        jacket1.setStatus(Product.ProductStatus.DANG_BAN);
        productRepository.save(jacket1);

        // Quần short
        Product shorts1 = new Product();
        shorts1.setName("Quần Short Nam Kaki");
        shorts1.setDescription("Quần short nam chất liệu kaki, form regular fit, phù hợp mùa hè");
        shorts1.setPrice(new BigDecimal("229000"));
        shorts1.setImageUrl("https://images.unsplash.com/photo-1506629905607-c52b1f0e8e4e?w=500");
        shorts1.setStock(80);
        shorts1.setStatus(Product.ProductStatus.DANG_BAN);
        productRepository.save(shorts1);
    }
}

