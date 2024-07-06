package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.IngredientEntity;
import com.erp.repository.IngredientRepository;

@Service
public class IngredientServiceImp implements IngredientService{

	@Autowired
	private IngredientRepository ingredientRepository;
	@Override
	public void saveIngredient(IngredientEntity ingredient) {
		ingredientRepository.save(ingredient);
		
	}
	@Override
	public List<IngredientEntity> getAllIngredients() {
		// TODO Auto-generated method stub
		return ingredientRepository.findAll();
	}
	@Override
	public void deleteIngredient(Long id) {
		ingredientRepository.deleteById(id);
		
	}
	@Override
	public void updateIngredient(IngredientEntity ingredient) {
		ingredientRepository.save(ingredient);
		
	}

}
