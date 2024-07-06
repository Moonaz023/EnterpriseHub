package com.erp.service;

import org.springframework.stereotype.Service;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;
import com.erp.repository.StockRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StockServiceImp implements StockService {

	@Autowired
    private StockRepository stockRepository;
	@Override
	public void updateStock(ProductEntity product, int productionQuantity) {
		StockEntity stock = stockRepository.findByProduct(product);

        if (stock == null) {
            
            stock = new StockEntity();
            stock.setProduct(product);
            stock.setProductQuantity(productionQuantity);
        } else {
            
            stock.setProductQuantity(stock.getProductQuantity() + productionQuantity);
        }

        
        stockRepository.save(stock);
    
		
	}
	@Override
	public void updateStockQuantity(ProductEntity product, int productionQuantity, int productionQuantity2) {
		StockEntity stock = stockRepository.findByProduct(product);

        if (stock == null) {
            
            stock = new StockEntity();
            stock.setProduct(product);
            stock.setProductQuantity(productionQuantity);
        } else {
            
        	 stock.setProductQuantity(stock.getProductQuantity() + (productionQuantity - productionQuantity2));
        }

        
        stockRepository.save(stock);
		
	}
	@Override
	public void updateStockWhenProductChanged(ProductEntity oldProduct, ProductEntity newProduct, int newQuantity,
			int oldQuantity) {
		StockEntity oldStock = stockRepository.findByProduct(oldProduct);

        if (oldStock == null) {
            
        	oldStock = new StockEntity();
        	oldStock.setProduct(oldProduct);
        	oldStock.setProductQuantity(oldQuantity);
        } else {
            
        	oldStock.setProductQuantity(oldStock.getProductQuantity() - oldQuantity);
        }
        stockRepository.save(oldStock);
        
        StockEntity newStock = stockRepository.findByProduct(newProduct);

        if (newStock == null) {
            
        	newStock = new StockEntity();
        	newStock.setProduct(newProduct);
        	newStock.setProductQuantity(newQuantity);
        } else {
            
        	newStock.setProductQuantity(newStock.getProductQuantity() + newQuantity);
        }
        stockRepository.save(newStock);
        
        
		
	}
	@Override
	public void updateStockWhenProductionDeteted(ProductEntity product, int quantity) {
		StockEntity Stock = stockRepository.findByProduct(product);
		Stock.setProductQuantity(Stock.getProductQuantity() - quantity);
		stockRepository.save(Stock);
	}
	@Override
	public List<StockEntity> getAllProductsStock() {
		// TODO Auto-generated method stub
		return stockRepository.findAll();
	}
	

}
