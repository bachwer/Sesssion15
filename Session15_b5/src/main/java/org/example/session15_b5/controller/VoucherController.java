package org.example.session15_b5.controller;

import org.example.session15_b5.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @PostMapping("/apply-voucher")
    public String applyVoucher(
            @RequestParam String code,
            Model model
    ) {
        Long userId = 1L;

        try {
            String result = voucherService.applyVoucher(userId, code);
            model.addAttribute("message", result);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "cart"; // 👉 quay lại giỏ hàng (UX chuẩn)
    }
}