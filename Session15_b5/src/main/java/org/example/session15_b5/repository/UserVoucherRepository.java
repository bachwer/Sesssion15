package org.example.session15_b5.repository;

import org.example.session15_b5.entity.UserVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVoucherRepository extends JpaRepository<UserVoucher, Long> {

    // ✅ kiểm tra user đã dùng voucher chưa
    boolean existsByUserIdAndVoucherId(Long userId, Long voucherId);
}