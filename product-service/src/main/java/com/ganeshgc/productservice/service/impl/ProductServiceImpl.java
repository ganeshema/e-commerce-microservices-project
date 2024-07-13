package com.ganeshgc.productservice.service.impl;

import com.ganeshgc.productservice.dto.ProductDto;
import com.ganeshgc.productservice.model.Product;
import com.ganeshgc.productservice.repository.ProductRepository;
import com.ganeshgc.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
   @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        productRepository.save(product);
        log.info("product {} is created", product.getId());
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> productDtos=new ArrayList<>();
        List<Product> products=productRepository.findAll();
        for(Product product:products){
            ProductDto productDto =new ProductDto();
            BeanUtils.copyProperties(product, productDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

}
