package org.example.sesssion15_b3.controller;


import org.example.sesssion15_b3.entity.Order;
import org.example.sesssion15_b3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getOrder(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ALL") String status,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "desc") String Direction,
            Model model,
            Sort sort){

        Long userId = 1L;

        Page<Order> pageData = orderService.getOrders(
                userId, page, size, status, sortBy, Direction
        );
        model.addAttribute("orders", pageData.getContent());
        model.addAttribute("currentPage", pageData.getNumber());
        model.addAttribute("totalPages", pageData.getTotalPages());

        model.addAttribute("status", status);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", Direction);

        return "order-list";

    }
}
