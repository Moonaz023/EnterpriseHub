package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.entity.DistributorEntity;
import com.erp.service.DistributorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DistributorController {

	@Autowired
    private DistributorService distributorService;
	
	@GetMapping("/distributor")
	public String index() {
		return "Distributor";
	}
	@PostMapping("admin/saveDistributor")
	public String saveProduct(@ModelAttribute DistributorEntity distributor )
	{
		System.out.println(distributor);
		distributorService.saveDistributor(distributor);
		return "redirect:/";
	}
	@GetMapping("/getAllDistributors")
	@ResponseBody
	public List<DistributorEntity> getAllDistributors(Model m, HttpSession session) {
		
	    List<DistributorEntity> listOfdistributor = distributorService.getAlldistributor();

	    return listOfdistributor;
	}
	@DeleteMapping("admin/deleteDistributor")
	@ResponseBody
	public String deleteDistributor(@RequestParam("id") long id) {
		distributorService.deleteDistributor(id);
	    return "Distributor deleted successfully";
	}
	@PostMapping("admin/updateDistributor")
    @ResponseBody
    public String updateDistributor( @ModelAttribute DistributorEntity updatedDistributor,HttpSession session ) {
        
		distributorService.updateDistributor(updatedDistributor);

        return "Distributor record updated successfully";
    }
	@GetMapping("/DistributorProfile/{id}")
	public String distributorProfile(@PathVariable Long id, Model model) {
	    DistributorHistoryDTO distributorHistory = distributorService.distributorProfile(id);
	    model.addAttribute("distributorHistory", distributorHistory);
	    return "DistributorProfile";
	}

	@GetMapping("/DistributorHistory/{id}")
	@ResponseBody
	public DistributorHistoryDTO DistributorHistory(@PathVariable Long id)
	{
		return distributorService.distributorProfile(id);
		
	}
}
