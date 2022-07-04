package com.codecool.vizsgaremek.service;

import com.codecool.vizsgaremek.entity.Order;
import com.codecool.vizsgaremek.entity.OrderItem;
import com.codecool.vizsgaremek.entity.dto.SaveOrderDto;
import com.codecool.vizsgaremek.entity.dto.UpdateOrderDto;
import com.codecool.vizsgaremek.exception.OrderNotFoundException;
import com.codecool.vizsgaremek.repository.OrderItemRepository;
import com.codecool.vizsgaremek.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository,OrderItemRepository orderItemRepository,CustomerService customerService,ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository=orderItemRepository;
        this.customerService=customerService;
        this.productService=productService;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findOrderById(long id){
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(SaveOrderDto saveOrderDto){
        Order orderToSave= orderRepository.save(Order.builder()
                .customer(customerService.findCustomerById(saveOrderDto.getCustomerId()))
                .orderItems(null)
                .orderTime(LocalDateTime.now())
                .build());
        long orderId=orderToSave.getId();
        List<OrderItem> orderItems = getOrderItems(saveOrderDto, orderId);
        orderItemRepository.saveAll(orderItems);
        orderToSave.setOrderItems(orderItems);
        return orderRepository.save(orderToSave);
    }

    private List<OrderItem> getOrderItems(SaveOrderDto saveOrderDto, long orderId) {
        return saveOrderDto.getSaveOrderItemDtos().stream()
                .map(x->x.convertToEntity(orderId,productService.findProductById(x.getProductId()),findOrderById(orderId))).toList();
    }


    public Order updateOrder(UpdateOrderDto updateOrderDto){
        Order orderToUpdate =findOrderById(updateOrderDto.getId());
        orderToUpdate.setCustomer(updateOrderDto.getCustomer());
        orderToUpdate.setOrderItems(updateOrderDto.getOrderItems());
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
