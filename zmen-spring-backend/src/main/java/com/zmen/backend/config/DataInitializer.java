package com.zmen.backend.config;

import com.zmen.backend.entity.*;
import com.zmen.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    
    // @Autowired
    // private CategoryRepository categoryRepository;
    
    // @Autowired
    // private BrandRepository brandRepository;
    
    // @Autowired
    // private ThongTinNguoiDungRepository thongTinNguoiDungRepository;
    
    // @Autowired
    // private DiaChiNguoiDungRepository diaChiNguoiDungRepository;
    
    // @Autowired
    // private GioHangRepository gioHangRepository;
    
    // @Autowired
    // private HoaDonRepository hoaDonRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeUsers();
        initializeProducts();
    }
    
    private void initializeCategoriesAndBrands() {
        if (categoryRepository.count() == 0) {
            Category category1 = new Category("Áo sơ mi", "ao-so-mi", "Áo sơ mi nam các loại");
            Category category2 = new Category("Áo polo", "ao-polo", "Áo polo nam thời trang");
            Category category3 = new Category("Áo khoác", "ao-khoac", "Áo khoác nam cao cấp");
            Category category4 = new Category("Quần", "quan", "Quần nam đa dạng");
            Category category5 = new Category("Thể thao", "the-thao", "Đồ thể thao nam");
            Category category6 = new Category("Phụ kiện", "phu-kien", "Phụ kiện thời trang nam");
            
            categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5, category6));
        }
        
        if (brandRepository.count() == 0) {
            Brand brand1 = new Brand("ZMEN", "zmen", "Thương hiệu thời trang nam ZMEN");
            Brand brand2 = new Brand("ZMEN Premium", "zmen-premium", "Dòng sản phẩm cao cấp ZMEN");
            Brand brand3 = new Brand("ZMEN Sport", "zmen-sport", "Dòng thể thao ZMEN");
            Brand brand4 = new Brand("ZMEN Denim", "zmen-denim", "Dòng jean ZMEN");
            Brand brand5 = new Brand("ZMEN Office", "zmen-office", "Dòng công sở ZMEN");
            
            brandRepository.saveAll(Arrays.asList(brand1, brand2, brand3, brand4, brand5));
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            // Create admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@zmen.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setFullName("Administrator");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);

            // Create employee user
            User employee = new User();
            employee.setUsername("employee");
            employee.setEmail("employee@zmen.com");
            employee.setPassword(passwordEncoder.encode("employee123"));
            employee.setFullName("Employee User");
            employee.setRole(User.Role.EMPLOYEE);
            userRepository.save(employee);

            // Create customer user
            User customer = new User();
            customer.setUsername("customer");
            customer.setEmail("customer@zmen.com");
            customer.setPassword(passwordEncoder.encode("customer123"));
            customer.setFullName("Customer User");
            customer.setRole(User.Role.CUSTOMER);
            userRepository.save(customer);

            System.out.println("Sample users created:");
            System.out.println("Admin: admin / admin123");
            System.out.println("Employee: employee / employee123");
            System.out.println("Customer: customer / customer123");
        }
    }

    private void initializeProducts() {
        if (productRepository.count() == 0) {
            // Create sample products
            Product product1 = new Product();
            product1.setName("Áo Sơ Mi Trắng Classic");
            product1.setDescription("Áo sơ mi trắng cao cấp, chất liệu cotton 100%, thiết kế thanh lịch phù hợp cho công sở và dự tiệc.");
            product1.setPrice(new BigDecimal("450000"));
            product1.setOriginalPrice(new BigDecimal("550000"));
            product1.setDiscountPercentage(new BigDecimal("18"));
            product1.setCategory("Áo sơ mi");
            product1.setBrand("ZMEN");
            product1.setImageUrl("/assets/clothing_display_1.jpg");
            product1.setRating(new BigDecimal("4.8"));
            product1.setReviewsCount(124);
            product1.setStockQuantity(50);
            product1.setSoldQuantity(89);
            product1.setFeatured(true);
            product1.setBestSeller(true);
            productRepository.save(product1);

            Product product2 = new Product();
            product2.setName("Bộ Quần Áo Thể Thao");
            product2.setDescription("Bộ quần áo thể thao nam cao cấp, chất liệu thấm hút mồ hôi tốt, thiết kế năng động.");
            product2.setPrice(new BigDecimal("680000"));
            product2.setCategory("Thể thao");
            product2.setBrand("ZMEN Sport");
            product2.setImageUrl("/assets/clothing_display_2.webp");
            product2.setRating(new BigDecimal("4.6"));
            product2.setReviewsCount(89);
            product2.setStockQuantity(30);
            product2.setSoldQuantity(45);
            product2.setFeatured(true);
            product2.setNew(true);
            productRepository.save(product2);

            Product product3 = new Product();
            product3.setName("Áo Khoác Blazer");
            product3.setDescription("Áo khoác blazer nam sang trọng, chất liệu wool blend, thiết kế hiện đại phù hợp cho các dịp quan trọng.");
            product3.setPrice(new BigDecimal("890000"));
            product3.setOriginalPrice(new BigDecimal("1200000"));
            product3.setDiscountPercentage(new BigDecimal("26"));
            product3.setCategory("Áo khoác");
            product3.setBrand("ZMEN Premium");
            product3.setImageUrl("/assets/clothing_display_3.jpg");
            product3.setRating(new BigDecimal("4.9"));
            product3.setReviewsCount(156);
            product3.setStockQuantity(20);
            product3.setSoldQuantity(78);
            product3.setFeatured(true);
            product3.setBestSeller(true);
            productRepository.save(product3);

            Product product4 = new Product();
            product4.setName("Quần Jean Slim Fit");
            product4.setDescription("Quần jean nam slim fit, chất liệu denim cao cấp, form dáng ôm vừa phải, phong cách trẻ trung.");
            product4.setPrice(new BigDecimal("520000"));
            product4.setCategory("Quần");
            product4.setBrand("ZMEN Denim");
            product4.setImageUrl("/assets/clothing_display_4.jpg");
            product4.setRating(new BigDecimal("4.7"));
            product4.setReviewsCount(203);
            product4.setStockQuantity(40);
            product4.setSoldQuantity(112);
            product4.setFeatured(true);
            productRepository.save(product4);

            Product product5 = new Product();
            product5.setName("Áo Polo Nam Classic");
            product5.setDescription("Áo polo nam chất liệu pique cotton, thiết kế cổ điển, màu sắc đa dạng.");
            product5.setPrice(new BigDecimal("320000"));
            product5.setCategory("Áo polo");
            product5.setBrand("ZMEN");
            product5.setImageUrl("/assets/clothing_display_1.jpg");
            product5.setRating(new BigDecimal("4.5"));
            product5.setReviewsCount(78);
            product5.setStockQuantity(60);
            product5.setSoldQuantity(34);
            product5.setNew(true);
            productRepository.save(product5);

            Product product6 = new Product();
            product6.setName("Quần Kaki Công Sở");
            product6.setDescription("Quần kaki nam công sở, chất liệu cotton pha, form dáng straight fit thoải mái.");
            product6.setPrice(new BigDecimal("420000"));
            product6.setOriginalPrice(new BigDecimal("480000"));
            product6.setDiscountPercentage(new BigDecimal("12"));
            product6.setCategory("Quần");
            product6.setBrand("ZMEN Office");
            product6.setImageUrl("/assets/clothing_display_2.webp");
            product6.setRating(new BigDecimal("4.4"));
            product6.setReviewsCount(92);
            product6.setStockQuantity(35);
            product6.setSoldQuantity(56);
            productRepository.save(product6);

            System.out.println("Sample products created: " + productRepository.count() + " products");
        }
    }
    
    private void initializeUserProfiles() {
        if (thongTinNguoiDungRepository.count() == 0) {
            User admin = userRepository.findByUsername("admin").orElse(null);
            User employee = userRepository.findByUsername("employee").orElse(null);
            User customer = userRepository.findByUsername("customer").orElse(null);
            
            if (admin != null) {
                ThongTinNguoiDung adminProfile = new ThongTinNguoiDung(admin, "Quản trị viên", "admin@zmen.com", "0901234567");
                adminProfile.setGioiTinh("Nam");
                adminProfile.setNgaySinh(LocalDate.of(1985, 5, 15));
                adminProfile.setNgheNghiep("Quản lý");
                thongTinNguoiDungRepository.save(adminProfile);
            }
            
            if (employee != null) {
                ThongTinNguoiDung employeeProfile = new ThongTinNguoiDung(employee, "Nhân viên bán hàng", "employee@zmen.com", "0901234568");
                employeeProfile.setGioiTinh("Nữ");
                employeeProfile.setNgaySinh(LocalDate.of(1990, 8, 20));
                employeeProfile.setNgheNghiep("Nhân viên");
                thongTinNguoiDungRepository.save(employeeProfile);
            }
            
            if (customer != null) {
                ThongTinNguoiDung customerProfile = new ThongTinNguoiDung(customer, "Khách hàng VIP", "customer@zmen.com", "0901234569");
                customerProfile.setGioiTinh("Nam");
                customerProfile.setNgaySinh(LocalDate.of(1992, 12, 10));
                customerProfile.setNgheNghiep("Kỹ sư");
                customerProfile.setThuNhapHangThang(25000000L);
                thongTinNguoiDungRepository.save(customerProfile);
            }
            
            System.out.println("User profiles created");
        }
    }
    
    private void initializeAddresses() {
        if (diaChiNguoiDungRepository.count() == 0) {
            User customer = userRepository.findByUsername("customer").orElse(null);
            
            if (customer != null) {
                // Địa chỉ mặc định
                DiaChiNguoiDung address1 = new DiaChiNguoiDung(customer, "Nhà riêng", "Khách hàng VIP", 
                    "0901234569", "123 Nguyễn Văn Cừ", "Phường 4", "Quận 5", "TP.HCM");
                address1.setLoaiDiaChi("Nhà riêng");
                address1.setLaDiaChiMacDinh(true);
                diaChiNguoiDungRepository.save(address1);
                
                // Địa chỉ công ty
                DiaChiNguoiDung address2 = new DiaChiNguoiDung(customer, "Công ty", "Khách hàng VIP", 
                    "0901234569", "456 Lê Văn Sỹ", "Phường 12", "Quận 3", "TP.HCM");
                address2.setLoaiDiaChi("Công ty");
                diaChiNguoiDungRepository.save(address2);
            }
            
            System.out.println("Sample addresses created");
        }
    }
    
    private void initializeSampleOrders() {
        if (hoaDonRepository.count() == 0) {
            User customer = userRepository.findByUsername("customer").orElse(null);
            
            if (customer != null) {
                // Đơn hàng đã hoàn thành
                HoaDon order1 = new HoaDon("HD001", customer, "ONLINE");
                order1.setTongSoLuong(2);
                order1.setTongTienHang(new BigDecimal("970000"));
                order1.setPhiVanChuyen(new BigDecimal("30000"));
                order1.setTongTienThanhToan(new BigDecimal("1000000"));
                order1.setTenNguoiNhan("Khách hàng VIP");
                order1.setSoDienThoaiNguoiNhan("0901234569");
                order1.setDiaChiGiaoHang("123 Nguyễn Văn Cừ, Phường 4, Quận 5, TP.HCM");
                order1.setPhuongThucThanhToan("STRIPE");
                order1.setTrangThaiThanhToan("PAID");
                order1.setTrangThaiDonHang("DELIVERED");
                order1.setNgayThanhToan(LocalDateTime.now().minusDays(5));
                order1.setNgayXacNhan(LocalDateTime.now().minusDays(4));
                order1.setNgayGiaoHang(LocalDateTime.now().minusDays(2));
                order1.setNgayHoanThanh(LocalDateTime.now().minusDays(1));
                hoaDonRepository.save(order1);
                
                // Đơn hàng đang xử lý
                HoaDon order2 = new HoaDon("HD002", customer, "ONLINE");
                order2.setTongSoLuong(1);
                order2.setTongTienHang(new BigDecimal("450000"));
                order2.setPhiVanChuyen(new BigDecimal("30000"));
                order2.setTongTienThanhToan(new BigDecimal("480000"));
                order2.setTenNguoiNhan("Khách hàng VIP");
                order2.setSoDienThoaiNguoiNhan("0901234569");
                order2.setDiaChiGiaoHang("456 Lê Văn Sỹ, Phường 12, Quận 3, TP.HCM");
                order2.setPhuongThucThanhToan("STRIPE");
                order2.setTrangThaiThanhToan("PAID");
                order2.setTrangThaiDonHang("PROCESSING");
                order2.setNgayThanhToan(LocalDateTime.now().minusDays(1));
                order2.setNgayXacNhan(LocalDateTime.now().minusDays(1));
                hoaDonRepository.save(order2);
            }
            
            System.out.println("Sample orders created");
        }
    }
}
