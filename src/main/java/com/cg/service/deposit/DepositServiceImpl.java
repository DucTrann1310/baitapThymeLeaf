package com.cg.service.deposit;

import com.cg.model.Customer;
import com.cg.model.Deposit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepositServiceImpl implements IDepositService{

    private static final List<Deposit> deposits = new ArrayList<>();
    private static long id = 1L;
    @Override
    public List<Deposit> findAll() {
        return null;
    }

    @Override
    public Deposit findById(Long id) {
        return null;
    }

    @Override
    public void create(Deposit deposit) {
        deposit.setId(id++);
        deposit.setCreateAt(LocalDateTime.now());
        deposits.add(deposit);

    }

    @Override
    public void update(Long aLong, Deposit deposit) {

    }

    @Override
    public void removeById(Long aLong) {

    }
}
