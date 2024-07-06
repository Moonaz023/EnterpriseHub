package com.erp.service;

import java.util.List;

import com.erp.entity.VendorEntity;

public interface VendorService {

	void saveVendor(VendorEntity vendor);

	List<VendorEntity> getAllVendor();

	void deleteVendor(long id);

	void updateVendor(VendorEntity updatedVendor);

}
