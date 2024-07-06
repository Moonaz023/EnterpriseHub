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

import com.erp.entity.ProductEntity;
import com.erp.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String index() {
		return "Product";
	}

	@GetMapping("/getAllProducts")
	@ResponseBody
	public List<ProductEntity> getAllproduct(Model m, HttpSession session) {

		List<ProductEntity> listOfproduct = productService.getAllproduct();

		return listOfproduct;
	}

	@PostMapping("admin/saveProduct")
	public String saveProduct(@ModelAttribute ProductEntity product) {
		System.out.println(product);
		productService.saveProduct(product);
		return "redirect:/";
	}

	@PostMapping("admin/updateProduct")
	@ResponseBody
	public String updateProduct(@ModelAttribute ProductEntity updatedProduct, HttpSession session) {

		productService.updateProduct(updatedProduct);

		return "Product record updated successfully";
	}

	@DeleteMapping("admin/deleteProduct")
	@ResponseBody
	public String deleteProduct(@RequestParam("id") long id) {
		productService.deleteProduct(id);
		return "Product deleted successfully";
	}

	@GetMapping("/getProductById")
	@ResponseBody
	public ProductEntity getProductById(@RequestParam("id") long id) {
		return productService.getProductById(id);
	}

}
