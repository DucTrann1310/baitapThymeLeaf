package com.cg.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transfer {
    private Long id;
    private LocalDateTime createAt;
    private Boolean deleted;
    private Long fee;
    private BigDecimal feeAmount;
    private BigDecimal transactionAmount;
    private BigDecimal transferAmount;
    private Customer sender;
    private Customer reciptent;

    public Transfer(Long id, LocalDateTime createAt, Boolean deleted, Long fee, BigDecimal feeAmount, BigDecimal transactionAmount, BigDecimal transferAmount, Customer sender, Customer reciptent) {
        this.id = id;
        this.createAt = createAt;
        this.deleted = deleted;
        this.fee = fee;
        this.feeAmount = feeAmount;
        this.transactionAmount = transactionAmount;
        this.transferAmount = transferAmount;
        this.sender = sender;
        this.reciptent = reciptent;
    }

    public Transfer() {
    }

    public Transfer(Customer sender, Long fee) {
        this.sender = sender;
        this.fee = fee;
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

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getReciptent() {
        return reciptent;
    }

    public void setReciptent(Customer reciptent) {
        this.reciptent = reciptent;
    }
}
