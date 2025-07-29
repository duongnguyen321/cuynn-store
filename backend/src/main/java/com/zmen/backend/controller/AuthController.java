package com.zmen.backend.controller;

import com.zmen.backend.dto.*;
import com.zmen.backend.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description = "API xác thực và phân quyền người dùng")
@CrossOrigin(origins = "*")
public class AuthController {

    // @Autowired
    // private AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Đăng ký tài khoản", description = "Đăng ký tài khoản người dùng mới")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        // User user = authService.register(request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đăng ký thành công");
        response.put("userId", 1L);
        response.put("email", request.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Đăng nhập", description = "Đăng nhập vào hệ thống")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        // JwtResponse response = authService.login(request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đăng nhập thành công");
        response.put("token", "jwt_token_here");
        response.put("user", Map.of("id", 1L, "email", request.getEmail()));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    @Operation(summary = "Đăng xuất", description = "Đăng xuất khỏi hệ thống")
    public ResponseEntity<Map<String, Object>> logout(@RequestHeader("Authorization") String token) {
        // authService.logout(token);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đăng xuất thành công");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Làm mới token", description = "Làm mới JWT token sử dụng refresh token")
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody RefreshTokenRequest request) {
        // JwtResponse response = authService.refreshToken(request.getRefreshToken());
        Map<String, Object> response = new HashMap<>();
        response.put("token", "new_jwt_token_here");
        response.put("refreshToken", "new_refresh_token_here");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    @Operation(summary = "Quên mật khẩu", description = "Gửi email reset mật khẩu")
    public ResponseEntity<Map<String, Object>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // authService.forgotPassword(request.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Email reset mật khẩu đã được gửi");
        response.put("email", request.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset mật khẩu", description = "Reset mật khẩu với token")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody ResetPasswordRequest request) {
        // authService.resetPassword(request.getToken(), request.getNewPassword());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Mật khẩu đã được cập nhật thành công");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-email")
    @Operation(summary = "Xác thực email", description = "Xác thực địa chỉ email với mã OTP")
    public ResponseEntity<Map<String, Object>> verifyEmail(@RequestBody VerifyEmailRequest request) {
        // authService.verifyEmail(request.getToken());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Email đã được xác thực thành công");
        response.put("verified", true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend-verification")
    @Operation(summary = "Gửi lại mã xác thực", description = "Gửi lại email xác thực")
    public ResponseEntity<Map<String, Object>> resendVerification(@RequestBody ResendVerificationRequest request) {
        // authService.resendVerificationEmail(request.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Mã xác thực đã được gửi lại");
        response.put("email", request.getEmail());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    @Operation(summary = "Thông tin người dùng hiện tại", description = "Lấy thông tin người dùng đang đăng nhập")
    public ResponseEntity<Map<String, Object>> getCurrentUser(@RequestHeader("Authorization") String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", 1L);
        response.put("email", "user@example.com");
        response.put("hoTen", "Nguyễn Văn A");
        response.put("role", "CUSTOMER");
        response.put("verified", true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/staff/login")
    @Operation(summary = "Đăng nhập nhân viên", description = "Đăng nhập dành cho nhân viên và admin")
    public ResponseEntity<Map<String, Object>> staffLogin(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Đăng nhập thành công");
        response.put("token", "staff_jwt_token_here");
        response.put("staff", Map.of(
            "id", 1L,
            "email", request.get("email"),
            "hoTen", "Nhân viên A",
            "role", "STAFF",
            "cuaHang", Map.of("id", 1L, "ten", "Cửa hàng chính")
        ));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate-token")
    @Operation(summary = "Kiểm tra token", description = "Kiểm tra tính hợp lệ của JWT token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("valid", true);
        response.put("userId", 1L);
        response.put("role", "CUSTOMER");
        response.put("exp", System.currentTimeMillis() + 3600000); // 1 hour
        return ResponseEntity.ok(response);
    }
}