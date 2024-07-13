package com.ganesh.inventory_service.service.impl;

import com.ganesh.inventory_service.dto.InventoryDto;
import com.ganesh.inventory_service.repository.InventoryRepository;
import com.ganesh.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<InventoryDto> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryDto.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity()>0)
                                .build())
                .toList();
    }
}
