package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.entity.Order;
import com.codecool.vizsgaremek.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findOrderById(long id){
        return orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        Order orderToUpdate =findOrderById(order.getId());
        orderToUpdate.setCustomer(order.getCustomer());
        orderToUpdate.setOrderItems(order.getOrderItems());
        orderToUpdate.setOrderTime(order.getOrderTime());
        return orderRepository.save(orderToUpdate);
    }

    public void deleteOrder(long id){
        orderRepository.deleteById(id);
    }
}
