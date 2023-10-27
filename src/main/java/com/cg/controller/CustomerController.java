package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.Deposit;
import com.cg.model.Transfer;
import com.cg.model.Withdraw;
import com.cg.service.customer.CustomerServiceImpl;
import com.cg.service.customer.ICustomerService;
import com.cg.service.deposit.DepositServiceImpl;
import com.cg.service.deposit.IDepositService;
import com.cg.service.transfer.ITransferService;
import com.cg.service.transfer.TransferServiceImpl;
import com.cg.service.withdraw.IWithdrawService;
import com.cg.service.withdraw.WithdrawServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private ICustomerService customerService = new CustomerServiceImpl();
    private IDepositService depositService = new DepositServiceImpl();
    private IWithdrawService withdrawService = new WithdrawServiceImpl();
    private ITransferService transferService = new TransferServiceImpl();

    @GetMapping
    public String showListPage(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "customer/list";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("customer", new Customer());

        return "customer/create";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable long id, Model model){
        model.addAttribute("customer", customerService.findById(id));

        return "customer/edit";
    }

    @GetMapping("/deposit/{id}")
    public String deposit(@PathVariable long id, Model model){
        model.addAttribute("deposit", new Deposit(customerService.findById(id)));

        return "/customer/deposit";
    }

    @GetMapping("/withdraw/{id}")
    public String withdraw(@PathVariable long id, Model model){

        model.addAttribute("withdraw", new Withdraw(customerService.findById(id)));

        return "/customer/withdraw";
    }
    @GetMapping("transfer/{id}")
    public String transfer(@PathVariable long id,Model model){

        model.addAttribute("transfer", new Transfer(customerService.findById(id),10L));
        model.addAttribute("reciptent", customerService.findReciptent(customerService.findById(id)));
        return "/customer/transfer";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id, Model model){

        model.addAttribute("customer",customerService.findById(id));

        return "/customer/delete";
    }

    @GetMapping("/transfer_histories")
    public String transferHistories(Model model){
        model.addAttribute("transfers", transferService.findAll());

        return "/customer/transferHistories";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {

        if (customer.getFullName().length() == 0) {
            model.addAttribute("success", false);
            model.addAttribute("message", "Created unsuccessful");
        }
        else {
            customerService.create(customer);

            model.addAttribute("success", true);
            model.addAttribute("message", "Created successfully");
            model.addAttribute("customer", new Customer());
        }

        return "customer/create";
    }


    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Customer customer, Model model){
        customerService.update(customer.getId(),customer);

        model.addAttribute("success", true);
        model.addAttribute("message", "Updated successfully");

        return "/customer/edit";
    }



    @PostMapping("/deposit/{id}")
    public String deposit(@PathVariable long id, @ModelAttribute Deposit deposit, Model model){

        if(deposit.getTransaction() != null && deposit.getTransaction().compareTo(BigDecimal.ZERO) >= 0){
            deposit.setCustomer(customerService.findById(id));
            depositService.create(deposit);
            customerService.updateBalanceFromDeposit(deposit.getCustomer(),deposit.getTransaction());

            model.addAttribute("success",true);
            model.addAttribute("message","Successful deposit transaction");
        }else
            if (deposit.getTransaction() == null || deposit.getTransaction().compareTo(BigDecimal.ZERO) <= 0){

            model.addAttribute("success",false);
            model.addAttribute("message", "Unsuccessful deposit transaction");
        }

        model.addAttribute("deposit", new Deposit(customerService.findById(deposit.getCustomer().getId())));
        return "/customer/deposit";
    }


    @PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable long id, @ModelAttribute Withdraw withdraw, Model model) {

        withdraw.setCustomer(customerService.findById(withdraw.getCustomer().getId()));

        if(withdraw.getTransaction().compareTo(withdraw.getCustomer().getBalance()) <= 0){
            withdrawService.create(withdraw);
            customerService.updateBalanceFromWithdraw(withdraw.getCustomer(),withdraw.getTransaction());

            model.addAttribute("success",true);
            model.addAttribute("message", "Withdrawed Successfully");
        }else
            if(withdraw.getTransaction().compareTo(withdraw.getCustomer().getBalance()) == 1
                || withdraw.getTransaction().compareTo(BigDecimal.ZERO) <= 0
                || withdraw.getTransaction() == null) {
            model.addAttribute("success",false);
            model.addAttribute("message","Withdrawed Unsuccessfully");
        }
        model.addAttribute("withdraw", new Withdraw(customerService.findById(withdraw.getCustomer().getId())));
        return "/customer/withdraw";
    }



    @PostMapping("/transfer/{id}")
    public String transfer( @ModelAttribute Transfer transfer, Model model){

        transfer.setSender(customerService.findById(transfer.getSender().getId()));
        transfer.setReciptent(customerService.findById(transfer.getReciptent().getId()));

        if(transfer.getTransactionAmount().compareTo(transfer.getSender().getBalance()) <=0){
            transferService.create(transfer);
            customerService.updateBalanceFromWithdraw(transfer.getSender(),transfer.getTransactionAmount());
            customerService.updateBalanceFromDeposit(transfer.getReciptent(),transfer.getTransferAmount());

            model.addAttribute("success",true);
            model.addAttribute("message", "Successful transfer transaction");
        }else
            if(transfer.getTransactionAmount().compareTo(transfer.getSender().getBalance()) >= 1
                || transfer.getTransferAmount().compareTo(BigDecimal.ZERO) <= 0
                || transfer.getTransferAmount() == null){
            model.addAttribute("success",false);
            model.addAttribute("message", "Unsuccessful transfer transaction");
        }

        model.addAttribute("transfer", new Transfer(customerService.findById(transfer.getSender().getId()),10L));
        model.addAttribute("reciptent", customerService.findReciptent(transfer.getSender()));
        return "customer/transfer";
    }


    @PostMapping("/remove/{id}")
    public String remove(@ModelAttribute Customer customer, RedirectAttributes redirect){

        customerService.removeById(customer.getId());

        redirect.addFlashAttribute("success",true);
        redirect.addFlashAttribute("message", "Deleted Successfully");

        return "redirect:/customers";
    }
}
