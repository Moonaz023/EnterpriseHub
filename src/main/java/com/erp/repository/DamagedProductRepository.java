package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erp.entity.DamagedProductEntity;

@Repository
public interface DamagedProductRepository extends JpaRepository<DamagedProductEntity, Long> {

}
