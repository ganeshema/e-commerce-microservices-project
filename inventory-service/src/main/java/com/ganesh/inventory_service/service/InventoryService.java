package com.ganesh.inventory_service.service;

import com.ganesh.inventory_service.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    List<InventoryDto> isInStock(List<String> skuCode);
}
