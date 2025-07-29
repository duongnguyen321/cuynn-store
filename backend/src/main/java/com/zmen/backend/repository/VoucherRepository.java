package com.zmen.backend.repository;

import com.zmen.backend.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    
    Optional<Voucher> findByVoucherCode(String voucherCode);
    
    @Query("SELECT v FROM Voucher v WHERE v.status = :status AND v.startDate <= :now AND v.endDate >= :now")
    List<Voucher> findActiveVouchers(@Param("status") Voucher.ActivityStatus status, @Param("now") LocalDateTime now);
    
    @Query("SELECT v FROM Voucher v WHERE v.voucherCode = :code AND v.status = :status AND v.startDate <= :now AND v.endDate >= :now")
    Optional<Voucher> findValidVoucherByCode(@Param("code") String code, @Param("status") Voucher.ActivityStatus status, @Param("now") LocalDateTime now);
    
    List<Voucher> findByStatusOrderByCreatedAtDesc(Voucher.ActivityStatus status);
    
    boolean existsByVoucherCode(String voucherCode);
}