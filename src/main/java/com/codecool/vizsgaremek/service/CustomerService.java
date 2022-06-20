package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.entity.Customer;
import com.codecool.vizsgaremek.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findCustomerById(long id){
        return customerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Customer customer){
        Customer customerToUpdate=findCustomerById(customer.getId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setTelephoneNumber(customer.getTelephoneNumber());
        customerToUpdate.setAddress(customer.getAddress());
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(long id){
        customerRepository.deleteById(id);
    }
}
