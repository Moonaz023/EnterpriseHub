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

import com.erp.entity.ProductionEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.service.PurchaseIngredientService;

import jakarta.servlet.http.HttpSession;
@Controller
public class PurchaseIngredientController {
	@Autowired
	private PurchaseIngredientService purchaseIngredientService;

	@GetMapping("/purchaseIngredient")
	public String purchaseIngredient() {
		return "purchaseIngredient";
	}
	@PostMapping("/savePurchasedIngredient")
	@ResponseBody
	public String savePurchasedIngredient(@ModelAttribute PurchaseIngredientEntity purchasedIngredient )
	{
		purchaseIngredientService.savePurchasedIngredient(purchasedIngredient);
		return "purchased Ingredient saved";
	}
	@GetMapping("/getAllPurchasedIngredients")
	@ResponseBody
	public List<PurchaseIngredientEntity> getAllPurchasedIngredients(Model m, HttpSession session) {
		
	    List<PurchaseIngredientEntity> listPurchasedIngredients = purchaseIngredientService.getAllPurchasedIngredients();

	    return listPurchasedIngredients;
	}
	@DeleteMapping("/deleteIngredientPurchaseRecord")
	@ResponseBody
	public String deleteIngredientPurchaseRecord(@RequestParam Long id) {
		 purchaseIngredientService.deleteIngredientPurchaseRecord(id);
		 return "Success";
	}
	
	@PostMapping("/updateIngredientPurchase")
	@ResponseBody
	public String updateIngredientPurchase(@ModelAttribute PurchaseIngredientEntity purchasedIngredient )
	{
		purchaseIngredientService.updateIngredientPurchase(purchasedIngredient);
		return "purchased Ingredient updated";
	}
}
