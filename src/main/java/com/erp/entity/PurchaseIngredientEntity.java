package com.erp.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class PurchaseIngredientEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateOfPurchase;

    @ManyToOne
    @JoinColumn(name = "ingredient", nullable = false)
    private IngredientEntity ingredient;
    
    private double quantity;
    private double bill;
    private double paid;
    
    @ManyToOne
    @JoinColumn(name = "vendor", nullable = false)
    private VendorEntity  vendor;
    
    @OneToMany(mappedBy = "purchaseId", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<IngredientBatchesStockEntity> ingredientBatchesStockEntity;
}
