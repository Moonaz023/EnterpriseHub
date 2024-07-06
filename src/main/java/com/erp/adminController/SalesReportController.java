package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.SalesReportEntity;

import com.erp.service.SalesReportService;
import jakarta.servlet.http.HttpSession;

@Controller
public class SalesReportController {
	@Autowired
	private SalesReportService salesReportService;

	@GetMapping("/SalesReport")
	public String salesReport() {
		return "SalesReport";
	}
	@GetMapping("/getAllSales")
	@ResponseBody
	public List<SalesReportEntity> getAllSales(Model m, HttpSession session) {
		
	    List<SalesReportEntity> listOfsales = salesReportService.getAllSales();

	    return listOfsales;
	}
}
