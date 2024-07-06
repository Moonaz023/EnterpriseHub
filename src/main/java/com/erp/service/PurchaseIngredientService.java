package com.erp.service;

import java.util.List;

import com.erp.entity.PurchaseIngredientEntity;

public interface PurchaseIngredientService {

	void savePurchasedIngredient(PurchaseIngredientEntity purchasedIngredient);

	List<PurchaseIngredientEntity> getAllPurchasedIngredients();

	void deleteIngredientPurchaseRecord(Long id);

	void updateIngredientPurchase(PurchaseIngredientEntity purchasedIngredient);

}
