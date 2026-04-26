package org.example.session15_b5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class UserVoucher {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long voucherId;

    private LocalDateTime usedDate;


    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public void setUsedDate(LocalDateTime usedDate) {
        this.usedDate = usedDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public LocalDateTime getUsedDate() {
        return usedDate;
    }
}