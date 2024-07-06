package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.PurchaseIngredientEntity;
@Repository
public interface PurchaseIngredientRepository extends JpaRepository<PurchaseIngredientEntity, Long>{

}
