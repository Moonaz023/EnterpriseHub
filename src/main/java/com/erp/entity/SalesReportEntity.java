package com.erp.entity;

import java.util.List;

import com.erp.dto.ItemAndQuantityDTO;
import com.erp.dto.RecipeDataDOT;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity
public class SalesReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String date;
	private String details;
	private double receptAmount;
	private double due;
	@ManyToOne
    @JoinColumn(name = "distributor_id", nullable = false)
    private DistributorEntity distributor;
	
	
	@ElementCollection
	@CollectionTable(joinColumns = @JoinColumn(name = "SalesId"))
	private List<ItemAndQuantityDTO> itemAndQuantity;
	
}
