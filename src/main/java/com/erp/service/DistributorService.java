package com.erp.service;

import java.util.List;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.entity.DistributorEntity;

public interface DistributorService {

	void saveDistributor(DistributorEntity distributor);

	List<DistributorEntity> getAlldistributor();

	void deleteDistributor(long id);

	void updateDistributor(DistributorEntity updatedDistributor);
	public DistributorHistoryDTO distributorProfile(Long id);

	
}
