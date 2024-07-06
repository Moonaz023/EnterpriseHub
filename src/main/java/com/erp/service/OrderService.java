package com.erp.service;

import java.util.List;

import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.erp.entity.OrderEntity;

public interface OrderService {

	String addOrder(OrderEntity order);

	List<OrderEntity> getAllOrder();

	CheckoutValidityResultDTO CheckOutValidityTest(long order_id);

	String checkoutNow(CheckoutPaymentDTO checkoutPayment);
	

}
