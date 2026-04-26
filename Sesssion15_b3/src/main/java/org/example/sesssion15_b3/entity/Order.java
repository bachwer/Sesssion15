package org.example.sesssion15_b3.entity;


import jakarta.persistence.*;
import org.example.sesssion15_b3.Enum.OrderStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Double totalAmount;

    private LocalDateTime createdDate;


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
