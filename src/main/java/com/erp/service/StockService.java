package com.erp.service;

import java.util.List;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;

public interface StockService {

	void updateStock(ProductEntity product, int productionQuantity);

	void updateStockQuantity(ProductEntity product, int productionQuantity, int productionQuantity2);

	void updateStockWhenProductChanged(ProductEntity oldProduct, ProductEntity newProduct, int newQuantity,
			int oldQuantity);

	void updateStockWhenProductionDeteted(ProductEntity product, int quantity);

	List<StockEntity> getAllProductsStock();

}
