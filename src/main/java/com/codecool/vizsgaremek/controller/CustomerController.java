package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.entity.Customer;
import com.codecool.vizsgaremek.entity.dto.SaveCustomerDto;
import com.codecool.vizsgaremek.entity.dto.UpdateCustomerDto;
import com.codecool.vizsgaremek.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable("id") long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping
    public Customer saveCustomer(@Valid @RequestBody SaveCustomerDto saveCustomerDto) {
        return customerService.saveCustomer(saveCustomerDto);
    }

    @PutMapping
    public Customer updateCustomer(@Valid @RequestBody UpdateCustomerDto updatecustomerDto) {
        return customerService.updateCustomer(updatecustomerDto);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
    }
}
