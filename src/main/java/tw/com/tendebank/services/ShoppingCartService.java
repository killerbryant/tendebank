package tw.com.tendebank.services;

import java.util.List;

import tw.com.tendebank.beans.Orders;

public interface ShoppingCartService {

	public boolean insert(Orders orders);
	
	public List<Orders> selectMonthOrdersByMemberNo(String memberNo);
	
	public List<Orders> selectHistoryOrdersByMemberNo(String memberNo);

	public boolean updateOrderByBankInfo(String orderNo, String afterFiveNumber);
}
