package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.IngredientEntity;
@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

}
