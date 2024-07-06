package com.erp.service;

import java.util.List;


import com.erp.entity.ProductEntity;


public interface ProductService {

	void saveProduct(ProductEntity product);
	List<ProductEntity> getAllproduct();
	ProductEntity getProductById(long id);
	void updateProduct(ProductEntity updatedProduct);
	void deleteProduct(long id);
}
