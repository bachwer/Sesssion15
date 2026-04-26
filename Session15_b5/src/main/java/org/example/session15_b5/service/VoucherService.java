package org.example.session15_b5.service;

import jakarta.transaction.Transactional;
import org.example.session15_b5.entity.UserVoucher;
import org.example.session15_b5.entity.Voucher;
import org.example.session15_b5.repository.UserVoucherRepository;
import org.example.session15_b5.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private UserVoucherRepository userVoucherRepository;

    @Transactional
    public String applyVoucher(Long userId, String code) {

        Voucher voucher = voucherRepository.findByCodeForUpdate(code)
                .orElseThrow(() -> new RuntimeException("Voucher không tồn tại"));

        // ❌ inactive
        if (!voucher.getStatus().equals("ACTIVE")) {
            throw new RuntimeException("Voucher đã bị khóa");
        }

        // ❌ hết hạn
        if (voucher.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Voucher đã hết hạn");
        }

        // ❌ hết lượt
        if (voucher.getQuantity() <= 0) {
            throw new RuntimeException("Voucher đã hết lượt sử dụng");
        }

        // ❌ user dùng rồi
        if (userVoucherRepository.existsByUserIdAndVoucherId(userId, voucher.getId())) {
            throw new RuntimeException("Bạn đã dùng voucher này rồi");
        }

        voucher.setQuantity(voucher.getQuantity() - 1);

        // ✅ lưu lịch sử
        UserVoucher uv = new UserVoucher();
        uv.setUserId(userId);
        uv.setVoucherId(voucher.getId());
        uv.setUsedDate(LocalDateTime.now());

        userVoucherRepository.save(uv);

        return "Áp dụng voucher thành công 🎉";
    }
}