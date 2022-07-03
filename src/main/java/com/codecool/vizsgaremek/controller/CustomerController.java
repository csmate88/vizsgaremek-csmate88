package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.entity.Customer;
import com.codecool.vizsgaremek.entity.dto.SaveCustomerDto;
import com.codecool.vizsgaremek.entity.dto.UpdateCustomerDto;
import com.codecool.vizsgaremek.exception.CustomerNotFoundException;
import com.codecool.vizsgaremek.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
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
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") long id) {
        try{
            return ResponseEntity.ok(customerService.findCustomerById(id));
        } catch (CustomerNotFoundException e){
            log.error("CUSTOMER NOT FOUND");
           return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody SaveCustomerDto saveCustomerDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("NOT VALID Customer");
            bindingResult.getAllErrors().forEach(x->log.error(x.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerService.saveCustomer(saveCustomerDto));
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody UpdateCustomerDto updatecustomerDto,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("NOT VALID Customer");
            bindingResult.getAllErrors().forEach(x->log.error(x.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerService.updateCustomer(updatecustomerDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") long id) {
        try{
            customerService.findCustomerById(id);
        }catch (CustomerNotFoundException e){
            log.error("CUSTOMER NOT FOUND");
            return ResponseEntity.notFound().build();
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
