package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.service.IGeneralService;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService extends IGeneralService<Customer, Long> {
    void updateBalanceFromDeposit(Customer customer, BigDecimal transaction);

    void updateBalanceFromWithdraw(Customer customer, BigDecimal transaction);
    List<Customer> findReciptent(Customer customer);
}
