package com.cg.service.transfer;

import com.cg.model.Deposit;
import com.cg.model.Transfer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransferService implements ITransferService{
    private static final List<Transfer> transfers = new ArrayList<>();
    private static long id = 1L;

    @Override
    public List<Transfer> findAll() {
        return null;
    }

    @Override
    public Transfer findById(Long aLong) {
        return null;
    }

    @Override
    public void create(Transfer transfer) {
        transfer.setId(id++);
        transfer.setCreateAt(LocalDateTime.now());
        transfers.add(transfer);
    }

    @Override
    public void update(Long aLong, Transfer transfer) {

    }

    @Override
    public void removeById(Long aLong) {

    }
}
