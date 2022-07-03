package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.entity.Order;
import com.codecool.vizsgaremek.entity.dto.SaveOrderDto;
import com.codecool.vizsgaremek.entity.dto.UpdateOrderDto;
import com.codecool.vizsgaremek.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findOrderById(@PathVariable("id") long id) {
        return orderService.findOrderById(id);
    }
    @PostMapping
    public Order saveOrder(@Valid @RequestBody SaveOrderDto saveOrderDto) {
        return orderService.saveOrder(saveOrderDto);
    }

    @PutMapping
    public Order updateOrder(@Valid @RequestBody UpdateOrderDto updateOrderDto) {
        return orderService.updateOrder(updateOrderDto);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
    }
}
