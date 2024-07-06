package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.IngredientEntity;
import com.erp.entity.IngredientStockEntity;
@Repository
public interface IngredientStockRepository extends JpaRepository<IngredientStockEntity, Long> {

	IngredientStockEntity findByIngredient(IngredientEntity ingredient);

}
