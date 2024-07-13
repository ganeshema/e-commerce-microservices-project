package com.ganesh.inventory_service.controller;

import com.ganesh.inventory_service.dto.InventoryDto;
import com.ganesh.inventory_service.model.Inventory;
import com.ganesh.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryDto> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
