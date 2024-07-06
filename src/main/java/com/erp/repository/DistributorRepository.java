package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.DistributorEntity;

@Repository
public interface DistributorRepository extends JpaRepository<DistributorEntity, Long> {

}
