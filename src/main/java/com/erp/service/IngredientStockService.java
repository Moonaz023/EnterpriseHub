package com.erp.service;

import java.util.List;

import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientEntity;
import com.erp.entity.IngredientStockEntity;
import com.erp.entity.PurchaseIngredientEntity;

public interface IngredientStockService {

	void saveIngredientStock(PurchaseIngredientEntity purchasedIngredient);

	double modifystock_purchagedlt(IngredientEntity ingredient, double quantity);

	List<IngredientStockEntity> getAllIngredientsStock();
	public boolean checkAvailablity(List<RecipeDataDOT> recipe);

}
