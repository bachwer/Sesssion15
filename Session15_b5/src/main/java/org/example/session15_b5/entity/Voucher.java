package org.example.session15_b5.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Voucher {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String code;

    private double discount;

    private int quantity;

    private String status; // ACTIVE / INACTIVE

    private LocalDateTime expiryDate;

    @Version
    private Long version; // vẫn nên có

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public double getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public Long getVersion() {
        return version;
    }
}