package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.StockEntity;
import com.erp.service.StockService;

import jakarta.servlet.http.HttpSession;

@Controller
public class StockController {
	@Autowired
    private StockService stockService;
	
	
	@GetMapping("/stock")
	public String stock() {
		return "Stock";
	}

	@GetMapping("/getAllProductsStock")
	@ResponseBody
	public List<StockEntity> getAllProductsStock(Model m, HttpSession session) {
		
	    List<StockEntity> listOfproductstock = stockService.getAllProductsStock();

	    return listOfproductstock;
	}
}
