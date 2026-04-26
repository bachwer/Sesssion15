package org.example.sesssion15_b3.service;


import org.example.sesssion15_b3.Enum.OrderStatus;
import org.example.sesssion15_b3.entity.Order;
import org.example.sesssion15_b3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    private static final List<String> ALLOWED_SORT = Arrays.asList("createdDate", "totalAmount");

    public Page<Order> getOrders(Long UserId, int page, int size, String status, String sortBy, String direction){

        if(!ALLOWED_SORT.contains(sortBy)){
            sortBy = "createdDate";
        }

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();


        if(page < 0) page = 0;

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Order> result;
        if(status.equalsIgnoreCase("ALL")){
            result = orderRepository.findOrderByUserId(UserId, pageable);

        }else{
            OrderStatus orderStatus = null;

            try {

                orderStatus = OrderStatus.valueOf(status);

            } catch (Exception e) {

                // fallback nếu user nhập bậy

            }

            if (orderStatus == null) {

                result = orderRepository.findOrderByUserId(UserId, pageable);

            } else {

                result = orderRepository.findOrderByUserIdAndStatus(UserId, orderStatus, pageable);

            }
        }



        if(page >= result.getTotalPages() && result.getTotalPages() > 0){
            pageable = PageRequest.of(result.getTotalPages() - 1, size, sort);
            return orderRepository.findOrderByUserId(UserId, pageable);

        }

        return result;

    }






}
