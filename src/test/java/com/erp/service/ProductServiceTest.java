package com.erp.service;

import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.erp.repository.ProductRepository;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

   
    private ProductService productService;
    @BeforeEach
    void setUp() {
    	this.productService = new ProductServiceImp(this.productRepository);
    }

    @Test
    public void testGetAllProducts() {
        // Create sample data
    	productService.getAllproduct();
    	verify(productRepository).findAll();
    }
}
