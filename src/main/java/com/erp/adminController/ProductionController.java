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
import com.erp.service.ProductionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductionController {
	
	@Autowired
    private ProductionService productionService;
	
	@GetMapping("/production")
	public String index() {
		return "production";
	}
	
	@GetMapping("/admin/getAllProductions")
	@ResponseBody
	public List<ProductionEntity> getAllproduction(Model m, HttpSession session) {
		
	    List<ProductionEntity> listOfproduct = productionService.getAllproduction();

	    return listOfproduct;
	}
	
	@PostMapping("admin/saveProduction")
	@ResponseBody
	public String saveProduct(@ModelAttribute ProductionEntity production )
	{
		System.out.println(production);
		return productionService.saveProduction(production);
		 
	}
	@PostMapping("admin/updateProduction")
    @ResponseBody
    public String updateProduction( @ModelAttribute ProductionEntity updatedProduction,HttpSession session ) {
        
		productionService.updateProduction(updatedProduction);

        return "Production record updated successfully";
    }
	
	@DeleteMapping("admin/deleteProduction")
	@ResponseBody
	public String deleteProduction(@RequestParam("id") long id) {
		productionService.deleteProduction(id);
	    return "Production deleted successfully";
	}

}
