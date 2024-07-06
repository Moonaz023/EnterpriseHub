package com.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.IngredientEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.repository.PurchaseIngredientRepository;

@Service
public class PurchaseIngredientServiceImp implements PurchaseIngredientService {

	@Autowired
	private PurchaseIngredientRepository purchaseIngredientRepository;
	@Autowired
	private IngredientStockService ingredientStockService;
	@Override
	public void savePurchasedIngredient(PurchaseIngredientEntity purchasedIngredient) {
		purchaseIngredientRepository.save(purchasedIngredient);
		ingredientStockService.saveIngredientStock(purchasedIngredient);
		
		
	}
	@Override
	public List<PurchaseIngredientEntity> getAllPurchasedIngredients() {
		
		return purchaseIngredientRepository.findAll();
	}
	@Override
	public void deleteIngredientPurchaseRecord(Long id) {
		Optional<PurchaseIngredientEntity> purchaseIngredientOpt= purchaseIngredientRepository.findById(id);
		PurchaseIngredientEntity purchaseIngredient =purchaseIngredientOpt.get();
		//purchaseIngredient.getIngredient();
		IngredientEntity ingredient=purchaseIngredient.getIngredient();
		double quantity=purchaseIngredient.getQuantity();
		ingredientStockService.modifystock_purchagedlt(ingredient,quantity);
		purchaseIngredientRepository.deleteById(id);
		//return null;
	}
	@Override
	public void updateIngredientPurchase(PurchaseIngredientEntity purchasedIngredient) {
		
		
		Optional<PurchaseIngredientEntity> purchaseIngredientOpt= purchaseIngredientRepository.findById(purchasedIngredient.getId());
		PurchaseIngredientEntity old_purchaseIngredient =purchaseIngredientOpt.get();
		//purchaseIngredient.getIngredient();
		IngredientEntity ingredient=old_purchaseIngredient.getIngredient();
		double quantity=purchasedIngredient.getQuantity();
		ingredientStockService.modifystock_purchagedlt(ingredient,quantity);
		purchaseIngredientRepository.save(purchasedIngredient);
		ingredientStockService.saveIngredientStock(purchasedIngredient);
		
	}

}
