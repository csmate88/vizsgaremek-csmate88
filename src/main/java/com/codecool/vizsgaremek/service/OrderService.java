package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.entity.Order;
import com.codecool.vizsgaremek.entity.Product;
import com.codecool.vizsgaremek.entity.dto.SaveOrderDto;
import com.codecool.vizsgaremek.entity.dto.UpdateOrderDto;
import com.codecool.vizsgaremek.exception.OrderNotFoundException;
import com.codecool.vizsgaremek.repository.OrderRepository;
import com.codecool.vizsgaremek.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class OrderService {
    private final OrderRepository orderRepository;

    private final CustomerService customerService;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,CustomerService customerService,ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerService=customerService;
        this.productRepository=productRepository;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findOrderById(long id){
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(SaveOrderDto saveOrderDto){
        Order newOrder=Order.builder()
                .customer(customerService.findCustomerById(saveOrderDto.getCustomerId()))
                .products(saveOrderDto.getProducts())
                .orderTime(LocalDateTime.now())
                .build();
        productRepository.saveAll(saveOrderDto.getProducts());
        return orderRepository.save(newOrder);
    }

    public Order updateOrder(UpdateOrderDto updateOrderDto){
        Order orderToUpdate =findOrderById(updateOrderDto.getId());
        List<Product> orderToUpdateProductList=updateOrderDto.getProducts();
        orderToUpdateProductList.forEach(x->x.setOrder(orderToUpdate));

        orderToUpdate.setCustomer(customerService.findCustomerById(updateOrderDto.getCustomerId()));
        orderToUpdate.setProducts(orderToUpdateProductList);
        productRepository.saveAll(updateOrderDto.getProducts());
        return orderRepository.save(orderToUpdate);
    }

    public void deleteOrder(long id){
        orderRepository.deleteById(id);
    }

    public List<Order> findOrdersBeforeDate(String date) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1= LocalDate.parse(date,formatter);
        return orderRepository.findOrdersBeforeDate(date1.atTime(23,59));
    }
}
