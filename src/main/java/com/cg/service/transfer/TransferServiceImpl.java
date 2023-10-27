package com.cg.service.transfer;

import com.cg.model.Transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransferServiceImpl implements ITransferService{
    private static final List<Transfer> transfers = new ArrayList<>();
    private static long id = 1L;

    @Override
    public List<Transfer> findAll() {
        return transfers;
    }

    @Override
    public Transfer findById(Long aLong) {
        return null;
    }

    @Override
    public void create(Transfer transfer) {
        transfer.setId(id++);
        transfer.setCreateAt(LocalDateTime.now());
        transfer.setFee(10L);
        transfer.setFeeAmount(transfer.getTransferAmount().multiply(BigDecimal.valueOf(transfer.getFee())).divide(BigDecimal.valueOf(100)));
        transfer.setTransactionAmount(transfer.getTransferAmount().add(transfer.getFeeAmount()));
        transfers.add(transfer);
    }

    @Override
    public void update(Long aLong, Transfer transfer) {

    }

    @Override
    public void removeById(Long aLong) {

    }
}
