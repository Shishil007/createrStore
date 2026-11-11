package com.telusko.creatorstore.controllers;

import com.telusko.creatorstore.dtos.OrderRequest;
import com.telusko.creatorstore.entities.Order;
import com.telusko.creatorstore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping("/new")
    public Order addNewOrder(@Valid @RequestBody OrderRequest request) {
        return service.addNewOrder(request);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return service.getOrderById(id);
    }

}
