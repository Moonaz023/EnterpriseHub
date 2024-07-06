package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.IngredientBatchesStockEntity;
import com.erp.entity.IngredientEntity;
@Repository
public interface IngredientBatchesStockRepository extends JpaRepository<IngredientBatchesStockEntity, Long> {

	List<IngredientBatchesStockEntity> findByIngredient(IngredientEntity ingredient);


}
