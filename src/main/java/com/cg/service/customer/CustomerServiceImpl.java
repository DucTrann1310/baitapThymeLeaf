package com.cg.service.customer;

import com.cg.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private static final List<Customer> customers = new ArrayList<>();
    private static long id = 1L;

    static {
        customers.add(new Customer(id++, "NVA", "nva@co.cc", "2345", "28 Nguyễn Tri Phương", BigDecimal.ZERO, false));
    }

    @Override
    public List<Customer> findAll() {

        List<Customer> customerList = new ArrayList<>();
        for(Customer customer : customers){
            if(!customer.getDeleted()){
                customerList.add(customer);
            }
        }
            return customerList;

    }

    @Override
    public Customer findById(Long id) {
        for (Customer customer : customers) {
            if (id.equals(customer.getId())) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void create(Customer customer) {
        customer.setId(id++);
        customer.setBalance(BigDecimal.ZERO);
        customer.setDeleted(false);
        customers.add(customer);
    }

    @Override
    public void update(Long id, Customer customer) {
        int index = customers.indexOf(findById(id));
        customers.set(index, customer);
    }

    @Override
    public void removeById(Long id) {
        Customer customer = findById(id);
        customer.setDeleted(true);
        update(customer.getId(),customer);
    }

    @Override
    public void updateBalanceFromDeposit(Customer customer, BigDecimal transaction) {
        customer.setBalance(customer.getBalance().add(transaction));
        update(customer.getId(), customer);
    }

    @Override
    public void updateBalanceFromWithdraw(Customer customer, BigDecimal transaction) {
        customer.setBalance(customer.getBalance().subtract(transaction));
        update(customer.getId(), customer);
    }

    @Override
    public List<Customer> findReciptent(Customer customer) {
        List<Customer> customerList = findAll();
        List<Customer> reciptent = new ArrayList<>();
        for (Customer c : customerList) {
            if (c.getId() != customer.getId()) {
                reciptent.add(c);
            }
        }
        return reciptent;
    }
}
