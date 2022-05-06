package tw.com.tendebank.services;

import java.util.List;

import tw.com.tendebank.beans.Orders;

/**
 * 訂單業務邏輯 Service 介面
 * @author Edison
 *
 */
public interface OrdersService {

	public List<Orders> selectMonthOrdersByDeliver(String deliverStatus);
	
	public List<Orders> selectHistoryOrdersByDeliver(String deliverStatus);
}
