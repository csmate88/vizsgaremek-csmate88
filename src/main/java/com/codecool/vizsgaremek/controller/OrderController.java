package com.codecool.vizsgaremek.controller;

import com.codecool.vizsgaremek.entity.Order;
import com.codecool.vizsgaremek.entity.dto.SaveOrderDto;
import com.codecool.vizsgaremek.entity.dto.UpdateOrderDto;
import com.codecool.vizsgaremek.exception.OrderNotFoundException;
import com.codecool.vizsgaremek.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
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
    public ResponseEntity<Order> findOrderById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(orderService.findOrderById(id));
        } catch (OrderNotFoundException e){
            log.error("ORDER NOT FOUND");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/date")
    public List<Order> findOrdersBefore(@RequestParam String date){
        return orderService.findOrdersBeforeDate(date);
    }
    @PostMapping
    public ResponseEntity<Order> saveOrder(@Valid @RequestBody SaveOrderDto saveOrderDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("NOT VALID ORDER");
            bindingResult.getAllErrors().forEach(x->log.error(x.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.saveOrder(saveOrderDto));
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@Valid @RequestBody UpdateOrderDto updateOrderDto,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("NOT VALID ORDER");
            bindingResult.getAllErrors().forEach(x->log.error(x.getDefaultMessage()));
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(orderService.updateOrder(updateOrderDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") long id) {
        try{
            orderService.findOrderById(id);
        } catch (OrderNotFoundException e){
            log.error("ORDER NOT FOUND");
            return ResponseEntity.badRequest().build();
        }
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
