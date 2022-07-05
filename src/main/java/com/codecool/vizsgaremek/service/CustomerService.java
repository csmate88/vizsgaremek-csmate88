package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.entity.Customer;
import com.codecool.vizsgaremek.entity.dto.SaveCustomerDto;
import com.codecool.vizsgaremek.entity.dto.UpdateCustomerDto;
import com.codecool.vizsgaremek.exception.CustomerNotFoundException;
import com.codecool.vizsgaremek.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer saveCustomer(SaveCustomerDto saveCustomerDto){
        return customerRepository.save(Customer.builder()
                .name(saveCustomerDto.getName())
                .email(saveCustomerDto.getEmail())
                .telephoneNumber(saveCustomerDto.getTelephoneNumber())
                .address(saveCustomerDto.getAddress())
                .orders(new ArrayList<>())
                .build());
    }
    public Customer updateCustomer(UpdateCustomerDto updateCustomerDto){
        Customer customerToUpdate=findCustomerById(updateCustomerDto.getId());
        customerToUpdate.setName(updateCustomerDto.getName());
        customerToUpdate.setEmail(updateCustomerDto.getEmail());
        customerToUpdate.setTelephoneNumber(updateCustomerDto.getTelephoneNumber());
        customerToUpdate.setAddress(updateCustomerDto.getAddress());
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(long id){
        customerRepository.deleteById(id);
    }
}
