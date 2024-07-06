package com.erp.entity;

import java.util.List;

import com.erp.dto.RecipeDataDOT;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class IngredientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String ingredientCode;
	private String ingredientName;
	
	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
	//@JsonManagedReference
	 @JsonIgnore
    private List<IngredientBatchesStockEntity> ingredientBatchesStock;
	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<PurchaseIngredientEntity> purchaseIngredientEntity;
	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
    private List<IngredientStockEntity> ingredientStockEntity;
//	@OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
//	private List<RecipeDataDOT> recipeDataDOTs;
	
}
