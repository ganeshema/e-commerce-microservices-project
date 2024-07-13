package com.ganeshgc.productservice.controller;

import com.ganeshgc.productservice.dto.ProductDto;
import com.ganeshgc.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductDto product) {
        productService.createProduct(product);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }
}
