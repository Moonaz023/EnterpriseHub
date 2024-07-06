package com.erp.entity;

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
public class IngredientBatchesStockEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
    @JoinColumn(name = "ingredient", nullable = false)
	//@JsonBackReference
    private IngredientEntity ingredient;
    private double quantity;
    private double unitCost;
    @ManyToOne
    @JoinColumn(nullable = false)
    private PurchaseIngredientEntity purchaseId;
    
}
