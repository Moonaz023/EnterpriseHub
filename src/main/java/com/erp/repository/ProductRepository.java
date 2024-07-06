package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	@Query("SELECT p.name FROM ProductEntity p WHERE p.id = :productId")
    String findProductNameById(@Param("productId") Long productId);
	@Query("SELECT p.price FROM ProductEntity p WHERE p.id = :productId")
	double findProductPriceById(long productId);
}
