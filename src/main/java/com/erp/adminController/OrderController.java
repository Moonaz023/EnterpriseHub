package com.erp.adminController;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.erp.entity.OrderEntity;
import com.erp.repository.OrderRepository;
import com.erp.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	public OrderService orderService;
	
	@GetMapping("/order")
	public String index() {
		return "Order";
	}
	@PostMapping("/addOrder")
	@ResponseBody
	public String addOrder(@ModelAttribute OrderEntity order) {
		
		return orderService.addOrder(order);
	    
	}

	@GetMapping("/getodd")
	@ResponseBody
	public List<OrderEntity> getSecondProducts(Model m, HttpSession session) {
		return orderService.getAllOrder();
	    
	}
	@GetMapping("/checkOutValidity")
	@ResponseBody
	public CheckoutValidityResultDTO CheckOutValidityTest(@RequestParam long order_id) {
	
		return orderService.CheckOutValidityTest(order_id);
	    
	}
	@PostMapping("/checkoutNow")
	@ResponseBody
	public String checkoutNow(@ModelAttribute CheckoutPaymentDTO checkoutPayment) {
		
		return orderService.checkoutNow(checkoutPayment);
	    
	}

//	@GetMapping("/checkOutValidity")
//	@ResponseBody
//	public Boolean CheckOutValidityTest() {
//	
//		return orderService.CheckOutValidityTest();
//	    
//	}


}
