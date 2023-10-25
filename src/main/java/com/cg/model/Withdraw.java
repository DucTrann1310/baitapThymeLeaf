package com.cg.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Withdraw {
    private Long id;
    private LocalDateTime createAt;
    private Boolean deleted;
    private Customer customer;
    private BigDecimal transaction;

    public Withdraw(Long id, LocalDateTime createAt, Boolean deleted, Customer customer, BigDecimal transaction) {
        this.id = id;
        this.createAt = createAt;
        this.deleted = deleted;
        this.customer = customer;
        this.transaction = transaction;
    }

    public Withdraw() {
    }

    public Withdraw(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTransaction() {
        return transaction;
    }

    public void setTransaction(BigDecimal transaction) {
        this.transaction = transaction;
    }
}
