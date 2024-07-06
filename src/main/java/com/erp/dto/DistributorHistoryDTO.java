package com.erp.dto;

import java.util.List;
import java.util.Map;

import com.erp.entity.DistributorEntity;
import com.erp.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class DistributorHistoryDTO {
	
	private DistributorEntity distributor;
	private Map<ProductEntity, Integer> productSale;
	private double totalPaid;
	private double totalDue;

}
