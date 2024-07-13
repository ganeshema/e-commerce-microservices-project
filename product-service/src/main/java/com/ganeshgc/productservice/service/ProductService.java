package com.ganeshgc.productservice.service;

import com.ganeshgc.productservice.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDto product);
    List<ProductDto> getAllProducts();
}
