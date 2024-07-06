package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ProductBatchesStockRepository extends JpaRepository<ProductBatchesStockEntity, Long> {

	List<ProductBatchesStockEntity> findByProduct(ProductEntity product);

	@Modifying
	@Transactional
	@Query("UPDATE ProductBatchesStockEntity s SET s.quantity = s.quantity - :quantity WHERE s.production = :productionId")
	void modifyStockByProduction(ProductionEntity productionId, int quantity);

}
