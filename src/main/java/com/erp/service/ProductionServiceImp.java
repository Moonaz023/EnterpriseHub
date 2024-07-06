package com.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientBatchesStockEntity;
import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;
import com.erp.repository.ProductBatchesStockRepository;
import com.erp.repository.ProductionRepository;
import java.lang.String;

import jakarta.transaction.Transactional;

@Service
public class ProductionServiceImp implements ProductionService {

	@Autowired
	private ProductionRepository productionRepository;
	@Autowired
	private StockService stockService;
	@Autowired
	private IngredientStockService ingredientStockService;
	@Autowired
	private ProductBatchesStockRepository productBatchesStockRepository;

	@Override
	@Transactional
	public String saveProduction(ProductionEntity production) {

		double totalCost = 0;
		List<RecipeDataDOT> recipeDatas = production.getRecipe();
		if (ingredientStockService.checkAvailablity(recipeDatas)) {
			try {
				for (RecipeDataDOT recipeData : recipeDatas) {
					totalCost += ingredientStockService.modifystock_purchagedlt(recipeData.getIngredient(),
							recipeData.getIngredientQuantity());
				}
				production.setMargin(production.getProduct().getPrice()-(totalCost/production.getProductionQuantity()));
				ProductionEntity savedProduction = productionRepository.save(production);

				stockService.updateStock(savedProduction.getProduct(), savedProduction.getProductionQuantity());
				System.out.println("Total Cost=" + totalCost);

				ProductBatchesStockEntity productBatchesStock = new ProductBatchesStockEntity();
				productBatchesStock.setCostPerUnit(totalCost / production.getProductionQuantity());
				productBatchesStock.setProduct(production.getProduct());
				productBatchesStock.setProduction(savedProduction);
				productBatchesStock.setQuantity(savedProduction.getProductionQuantity());

				productBatchesStockRepository.save(productBatchesStock);

				return "ok";
			} catch (Exception e) {
				throw e;
			}
		} else {
			System.out.println("Not enough ingrediant");
			return "no";
		}

	}

	@Override
	public List<ProductionEntity> getAllproduction() {
		return productionRepository.findAll();
	}

	@Override
	public ProductionEntity getProductionById(long id) {
		Optional<ProductionEntity> productOptional = productionRepository.findById(id);
		return productOptional.orElse(null);
	}

	@Override
	public void updateProduction(ProductionEntity updatedProduction) {
		Optional<ProductionEntity> oldProductionOptional = productionRepository.findById(updatedProduction.getId());

		if (oldProductionOptional.isPresent()) {
			ProductionEntity oldProduction = oldProductionOptional.get();

			int oldQuantity = oldProduction.getProductionQuantity();
			int newQuantity = updatedProduction.getProductionQuantity();

			ProductEntity oldProduct = oldProduction.getProduct();
			ProductEntity newProduct = updatedProduction.getProduct();

			ProductionEntity savedProduction = productionRepository.save(updatedProduction);

			if (oldProduct.equals(newProduct) && oldQuantity != newQuantity) {

				stockService.updateStockQuantity(savedProduction.getProduct(), newQuantity, oldQuantity);
			} else if (!oldProduct.equals(newProduct)) {
				stockService.updateStockWhenProductChanged(oldProduct, newProduct, newQuantity, oldQuantity);
			}
		} else {

		}
		//Edit connection with ingredient also
	}

	@Override
	public void deleteProduction(long id) {
		Optional<ProductionEntity> Production = productionRepository.findById(id);
		if (Production.isPresent()) {
			ProductionEntity production = Production.get();

			ProductEntity Product = production.getProduct();
			int Quantity = production.getProductionQuantity();
			stockService.updateStockWhenProductionDeteted(Product, Quantity);
			
			//Delete connection with ingredient also
		}

		productionRepository.deleteById(id);

	}

}
