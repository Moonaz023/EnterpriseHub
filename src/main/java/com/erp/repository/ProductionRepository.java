package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.ProductionEntity;

public interface ProductionRepository extends JpaRepository<ProductionEntity, Long> {

}
