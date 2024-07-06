package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.VendorEntity;
import com.erp.repository.VendorRepository;

@Service
public class VendorServiceImp implements VendorService {
	@Autowired
	private VendorRepository vendorRepository;

	@Override
	public void saveVendor(VendorEntity vendor) {
		vendorRepository.save(vendor);
		
	}

	@Override
	public List<VendorEntity> getAllVendor() {
		return vendorRepository.findAll();
		 
	}

	@Override
	public void deleteVendor(long id) {
		vendorRepository.deleteById(id);
		
	}

	@Override
	public void updateVendor(VendorEntity updatedVendor) {
		vendorRepository.save(updatedVendor);
		
	}

}
