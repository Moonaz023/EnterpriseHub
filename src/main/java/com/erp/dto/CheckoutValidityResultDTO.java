package com.erp.dto;


import java.util.List;

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
public class CheckoutValidityResultDTO {

	private boolean success;
    private double totalPrice;
    private List<CheckoutDataDTO> details;
}
