package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.service.IngredientService;

import jakarta.servlet.http.HttpSession;

import com.erp.entity.IngredientEntity;

@Controller
public class IngredientController {

	@Autowired
    private IngredientService ingredientService;
	@GetMapping("/ingredients")
	public String Ingredients() {
		return "Ingredients";
	}
	@PostMapping("/addIngredient")
	@ResponseBody
	public String saveIngredient(@ModelAttribute IngredientEntity ingredient )
	{
		ingredientService.saveIngredient(ingredient);
		return "Ingredient Saved";
	}
	@GetMapping("/getAllIngredients")
	@ResponseBody
	public List<IngredientEntity> getAllIngredients(Model m, HttpSession session) {
		return ingredientService.getAllIngredients();
	    
	}
	
	@PostMapping("/updateIngredient")
	@ResponseBody
	public String updateIngredient(@ModelAttribute IngredientEntity ingredient )
	{
		ingredientService.updateIngredient(ingredient);
		return "Ingredient Updated";
	}
	@DeleteMapping("/deleteIngredient")
	@ResponseBody
	public String deleteIngredient(@RequestParam Long id)
	{
		ingredientService.deleteIngredient(id);
		return "Delete successful";
	}
}
