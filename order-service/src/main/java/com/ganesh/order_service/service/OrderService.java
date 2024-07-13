package com.ganesh.order_service.service;

import com.ganesh.order_service.dto.OrderDto;

public interface OrderService {

    void placeOrder(OrderDto orderDto);
}
