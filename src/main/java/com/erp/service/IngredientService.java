package com.erp.service;

import java.util.List;

import com.erp.entity.IngredientEntity;

public interface IngredientService {

	void saveIngredient(IngredientEntity ingredient);

	List<IngredientEntity> getAllIngredients();

	void deleteIngredient(Long id);

	void updateIngredient(IngredientEntity ingredient);

}
