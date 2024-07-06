package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;

public interface SalesReportRepository extends JpaRepository<SalesReportEntity, Long> {

	List<SalesReportEntity> findByDistributor(DistributorEntity distributor);

}
