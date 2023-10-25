package com.cg.service.withdraw;

import com.cg.model.Deposit;
import com.cg.model.Withdraw;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WithdrawService implements IWithdrawService{

    private static final List<Withdraw> withdraws = new ArrayList<>();
    private static long id = 1L;
    @Override
    public List<Withdraw> findAll() {
        return null;
    }

    @Override
    public Withdraw findById(Long aLong) {
        return null;
    }

    @Override
    public void create(Withdraw withdraw) {
        withdraw.setId(id++);
        withdraw.setCreateAt(LocalDateTime.now());
        withdraws.add(withdraw);
    }

    @Override
    public void update(Long aLong, Withdraw withdraw) {

    }

    @Override
    public void removeById(Long aLong) {

    }
}
