package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.PurchaseIngredientEntity;
import com.erp.repository.IngredientBatchesStockRepository;
import com.erp.repository.IngredientStockRepository;

import jakarta.transaction.Transactional;

import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientEntity;
import com.erp.entity.IngredientStockEntity;
import com.erp.entity.IngredientBatchesStockEntity;

@Service
public class IngredientStockServiceImp implements  IngredientStockService{
	@Autowired
	private IngredientStockRepository ingredientStockRepository;
	@Autowired
	private IngredientBatchesStockRepository ingredientBatchesStockRepository;

	@Override
	public void saveIngredientStock(PurchaseIngredientEntity purchasedIngredient) {
		IngredientStockEntity ingredientStock = ingredientStockRepository.findByIngredient(purchasedIngredient.getIngredient());
		IngredientBatchesStockEntity ingredientBatchesStock = new IngredientBatchesStockEntity();
		ingredientBatchesStock.setIngredient(purchasedIngredient.getIngredient());
    	ingredientBatchesStock.setQuantity(purchasedIngredient.getQuantity());
    	ingredientBatchesStock.setUnitCost(purchasedIngredient.getBill()/purchasedIngredient.getQuantity());
    	ingredientBatchesStock.setPurchaseId(purchasedIngredient);
        if (ingredientStock == null) {
            
        	ingredientStock = new IngredientStockEntity();
        	ingredientStock.setIngredient(purchasedIngredient.getIngredient());
        	ingredientStock.setIngredientQuantity(purchasedIngredient.getQuantity());
        } else {
        	ingredientStock.setIngredientQuantity(ingredientStock.getIngredientQuantity() + purchasedIngredient.getQuantity());
   
        }

        ingredientBatchesStockRepository.save(ingredientBatchesStock);
        ingredientStockRepository.save(ingredientStock);
		
	}

	@Override
	@Transactional
	public double modifystock_purchagedlt(IngredientEntity ingredient, double quantity) {
		IngredientStockEntity ingredientStock = ingredientStockRepository.findByIngredient(ingredient);
		
		ingredientStock.setIngredientQuantity(ingredientStock.getIngredientQuantity()-quantity);
		List <IngredientBatchesStockEntity> ingredientBatchesStock=ingredientBatchesStockRepository.findByIngredient(ingredient);
		double remaing=quantity;
		double cost=0;
		for(IngredientBatchesStockEntity it:ingredientBatchesStock) {
			if(it.getQuantity()>=remaing) {
				it.setQuantity(it.getQuantity()-remaing);
				cost+=it.getUnitCost()*remaing;
				remaing=0;
			}else {
				remaing-=it.getQuantity();
				cost+=it.getUnitCost()*it.getQuantity();
				it.setQuantity(0);
			}
			if(it.getQuantity()==0) {
				ingredientBatchesStockRepository.delete(it);
			}else {
				ingredientBatchesStockRepository.save(it);
			}
			if(remaing==0) break;
			
			
		}
		ingredientStockRepository.save(ingredientStock);
		System.out.println(cost);//testing
		return cost;
		
	}

	@Override
	public List<IngredientStockEntity> getAllIngredientsStock() {
		
		return ingredientStockRepository.findAll();
	}

	@Override
	public boolean checkAvailablity(List<RecipeDataDOT> recipe) {
		for(RecipeDataDOT item:recipe) {
			IngredientStockEntity ingredientStock = ingredientStockRepository.findByIngredient(item.getIngredient());
			if(ingredientStock==null || ingredientStock.getIngredientQuantity()<item.getIngredientQuantity())
			{
				return false;
			}
			
		}
		return true;
		
	}
	 

}
