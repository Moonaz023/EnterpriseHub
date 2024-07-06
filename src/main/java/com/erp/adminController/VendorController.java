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

import com.erp.entity.VendorEntity;
import com.erp.service.VendorService;

import jakarta.servlet.http.HttpSession;
@Controller
public class VendorController {
	
		@Autowired
	    private VendorService vendorService;
		
		@GetMapping("/vendor")
		public String VendorIndex() {
			return "Vendors";
		}
		@PostMapping("admin/saveVendor")
		@ResponseBody
		public String saveVendor(@ModelAttribute VendorEntity vendor )
		{
			System.out.println(vendor);
			vendorService.saveVendor(vendor);
			return "Vendor saved";
		}
		@GetMapping("/getAllVendors")
		@ResponseBody
		public List<VendorEntity> getAllVendors(Model m, HttpSession session) {
			
		    List<VendorEntity> listOfvendor = vendorService.getAllVendor();

		    return listOfvendor;
		}
		@DeleteMapping("admin/deleteVendor")
		@ResponseBody
		public String deleteVendor(@RequestParam("id") long id) {
			vendorService.deleteVendor(id);
		    return "Vendor deleted successfully";
		}
		@PostMapping("admin/updateVendor")
	    @ResponseBody
	    public String updateVendor( @ModelAttribute VendorEntity updatedVendor,HttpSession session ) {
	        
			vendorService.updateVendor(updatedVendor);

	        return "Vendor record updated successfully";
	    }
	}
