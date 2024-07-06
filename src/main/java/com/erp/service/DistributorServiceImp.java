package com.erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.dto.ItemAndQuantityDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.repository.DistributorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DistributorServiceImp implements DistributorService {
	@Autowired
	private DistributorRepository distributorRepository;

	@Autowired
	private SalesReportService salesReportService;

	@Override
	public void saveDistributor(DistributorEntity distributor) {
		distributor.setTotal_order(0);
		distributorRepository.save(distributor);

	}

	@Override
	public List<DistributorEntity> getAlldistributor() {
		return distributorRepository.findAll();
	}

	@Override
	public void deleteDistributor(long id) {
		distributorRepository.deleteById(id);

	}

	@Override
	public void updateDistributor(DistributorEntity updatedDistributor) {
		updatedDistributor.setTotal_order(updatedDistributor.getTotal_order());
		distributorRepository.save(updatedDistributor);

	}

	@Override
	public DistributorHistoryDTO distributorProfile(Long id) {
		Optional<DistributorEntity> distributorOptional = distributorRepository.findById(id);
		if (!distributorOptional.isPresent()) {
			throw new EntityNotFoundException("Distributor not found for ID: " + id);
		}
		
		DistributorEntity distributor = distributorOptional.get();
		List<SalesReportEntity> salesReport = salesReportService.getSalesReportByDistributor(distributor);
		Map<ProductEntity, Integer> productSale = new HashMap<>();
		double totalPaid=0;
		double totalDue=0;
		for (SalesReportEntity it : salesReport) {
			totalPaid+=it.getReceptAmount();
			totalDue+=it.getDue();
			List<ItemAndQuantityDTO> itemAndQuantityList = it.getItemAndQuantity();
			if (itemAndQuantityList == null) {
	            continue; 
	        }
	        
			for (ItemAndQuantityDTO itt : itemAndQuantityList) {
				productSale.put(itt.getProduct(),
						productSale.getOrDefault(itt.getProduct(), 0) + itt.getProductQuantity());
			}
		}
		
		//Test------------------
		
		/* for (var  entry : productSale.entrySet()) {
		        System.out.println("Product: " + entry.getKey().getName() + ", Quantity: " + entry.getValue());
		    }
		 System.out.println("totalPaid="+totalPaid+" totalDue="+totalDue);*/
		 
		 DistributorHistoryDTO distributorHistory= new DistributorHistoryDTO();
		 distributorHistory.setProductSale(productSale);
		 distributorHistory.setTotalDue(totalDue);
		 distributorHistory.setTotalPaid(totalPaid);
		 distributorHistory.setDistributor(distributor);
		 
		 return distributorHistory;
	}

}
