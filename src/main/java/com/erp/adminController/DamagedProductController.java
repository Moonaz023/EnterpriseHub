package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.DamagedProductEntity;
import com.erp.service.DamagedProductService;

@Controller
public class DamagedProductController {
	@Autowired
	private DamagedProductService damagedProductService;

	@GetMapping("/damagedProduct")
	public String index() {
		return "DamagedProduct";

	}

	@PostMapping("/saveDamagedProduct")
	@ResponseBody
	public String saveDamagedProduct(@ModelAttribute DamagedProductEntity damagedProduct) {
		damagedProductService.save(damagedProduct);
		return "ok";

	}
	@GetMapping("/getAllDamagedProduct")
	@ResponseBody
	public List<DamagedProductEntity> getAllDamagedProduct(){
		return damagedProductService.findAll();
	}

}
