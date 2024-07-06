package com.erp.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import com.erp.entity.ProductEntity;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveProduct() {
        // Create a ProductEntity
        ProductEntity product = new ProductEntity();
        product.setProductCode("p003");
        product.setName("ice cream");
        product.setCategory("chocolate");
        product.setPrice(30.0);
       // product.setDiscount(10.0);
        // Save the ProductEntity
        productRepository.save(product);

    }
}
