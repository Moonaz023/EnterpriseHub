package com.erp.dto;

import java.sql.Date;
import java.util.List;

import com.erp.entity.IngredientEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RecipeDataDOT {
	
	@ManyToOne
	private IngredientEntity ingredient;
	private double ingredientQuantity;

}
