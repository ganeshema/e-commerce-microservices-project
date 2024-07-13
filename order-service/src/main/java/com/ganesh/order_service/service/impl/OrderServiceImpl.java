package com.ganesh.order_service.service.impl;

import com.ganesh.order_service.dto.InventoryDto;
import com.ganesh.order_service.dto.OrderDto;
import com.ganesh.order_service.dto.OrderLineItemDto;
import com.ganesh.order_service.model.Order;
import com.ganesh.order_service.model.OrderLineItems;
import com.ganesh.order_service.repository.OrderRepository;
import com.ganesh.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems =orderDto.getOrderLineItemDtos()
                .stream().map(orderLineItemDto -> mapToDto(orderLineItemDto))
                .toList();
        order.setOrderLineItems(orderLineItems);
        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        // Call Inventory Service, and place order if product is in
        // stock
        InventoryDto[] inventoryDtos = webClientBuilder.build().get()
                .uri("http://INVENTORY-SERVICE/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryDto[].class)
                .block();
        boolean allProductsInStock=Arrays.stream(inventoryDtos)
                .allMatch(inventoryDto -> inventoryDto.getIsInStock());

        if (allProductsInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Stock is not available");
        }

    }
    private OrderLineItems mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItems;
    }
}

