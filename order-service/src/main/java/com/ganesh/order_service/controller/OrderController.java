package com.ganesh.order_service.controller;

import com.ganesh.order_service.dto.OrderDto;
import com.ganesh.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderDto orderDto) {
        orderService.placeOrder(orderDto);
        return "Successfully ! Order Placed ";
    }
}
