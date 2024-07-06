package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.IngredientStockEntity;
import com.erp.service.IngredientStockService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IngredientStockController {
	@Autowired
	private IngredientStockService ingredientStockService;

	@GetMapping("/ingredientstock")
	public String ingredient_stock() {
		return "ingredientstock";
	}

	@GetMapping("/getAllIngredientStock")
	@ResponseBody
	public List<IngredientStockEntity> getAllProductsStock(Model m, HttpSession session) {

		List<IngredientStockEntity> listOfIngredientsStock = ingredientStockService.getAllIngredientsStock();

		return listOfIngredientsStock;
	}
}
