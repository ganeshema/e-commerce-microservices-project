package com.ganesh.order_service.dto;

import com.ganesh.order_service.model.OrderLineItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private String orderNumber;
    private List<OrderLineItemDto> orderLineItemDtos;
}

