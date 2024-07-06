package com.erp.service;

import java.util.List;
import com.erp.entity.ProductionEntity;

public interface ProductionService {

	public String saveProduction(ProductionEntity production);
	List<ProductionEntity> getAllproduction();
	public ProductionEntity getProductionById(long id);
	void updateProduction(ProductionEntity updatedProduction);
	void deleteProduction(long id);
	
	    
}
